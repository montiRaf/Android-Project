package it.dib.uniba.apobus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BusStopListActivity extends AppCompatActivity {

    public static final String PICK_BUS_STOP_ACTION = Const.PKG + ".action.PICK_BUS_STOP_ACTION";

    private float mLatitude;
    private float mLongitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_list);
        final Intent inputIntent = getIntent();
        if(inputIntent != null){
            mLatitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
            mLongitute = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
            if(mLatitude != 0.0f && mLongitute != 0.0f){
                // facciamo qualcosa con i dati
            }else{
                // l'activity termina
                finish();
            }
        }else{
            finish();
        }
    }

    public void sendBusStopBack(final View view){
        final BusStop busStop = BusStop.Builder.create("myId", "myName")
                .withLocation(mLatitude, mLongitute)
                .withDirection("my direction")
                .build();
        final Intent backIntent = new Intent();
        backIntent.putExtra(BusStop.Keys.ID, busStop);
        setResult(RESULT_OK, backIntent);
        finish();
    }
}