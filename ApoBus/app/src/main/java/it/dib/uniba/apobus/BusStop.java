package it.dib.uniba.apobus;

// BUILDER PATTERN
// Separa la costruzione di un oggetto complesso dalla sua rappresentazione

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public final class BusStop implements Parcelable {

    public final String id;
    public final String name;
    public final String direction;
    public final float latitude;
    public final float longitude;

    public final static byte PRESENT = 1;
    public final static byte NOT_PRESENT = 0;

    public interface Keys{
        String ID = "id";
        String NAME = "name";
        String DIRECTION = "direction";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
    }

    private BusStop(final String id, final String name, final String direction, final float latitude, final float longitude){
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // inserimento delle proprietà della classe in un intent
    public void toIntent(final Intent intent){
        intent.putExtra(BusStop.Keys.ID, id);
        intent.putExtra(BusStop.Keys.NAME, name);
        intent.putExtra(BusStop.Keys.DIRECTION, direction);
        intent.putExtra(BusStop.Keys.LATITUDE, latitude);
        intent.putExtra(BusStop.Keys.LONGITUDE, longitude);
    }

    // estrazione dei dati dall'intent
    public static BusStop fromIntent(final Intent inputIntent){
        final String id = inputIntent.getStringExtra(BusStop.Keys.ID);
        final String name = inputIntent.getStringExtra(BusStop.Keys.NAME);
        final String direction = inputIntent.getStringExtra(BusStop.Keys.DIRECTION);
        final float latitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
        final float longitude = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
        final BusStop busStop = BusStop.Builder.create(id, name)
                .withDirection(direction)
                .withLocation(latitude, longitude)
                .build();
        return busStop;
    }

    // parcellizzazione
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(name);
        if(direction != null){
            dest.writeByte(PRESENT);
            dest.writeString(direction);
        }else{
            dest.writeByte(NOT_PRESENT);
        }
        if(latitude != 0.0){
            dest.writeByte(PRESENT);
            dest.writeFloat(latitude);
            dest.writeFloat(longitude);
        }else{
            dest.writeByte(NOT_PRESENT);
        }
    }

    public static class Builder{
        private String mId;
        private String mName;
        private String mDirection;
        private float mLatitude;
        private float mLongitude;
        private Builder(final String id, final String name){    // elementi obbligatori da inizializzare
            this.mId = id;
            this.mName = name;
        }

        public static Builder create(final String id, final String name){
            return new Builder(id, name);
        }

        public Builder withDirection(final String direction){
            this.mDirection = direction;
            return this;
        }

        public Builder withLocation(final float latitude, final float longitude){
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            return this;
        }

        public BusStop build(){
            return new BusStop(mId, mName, mDirection, mLatitude, mLongitude);
        }

    }

}
