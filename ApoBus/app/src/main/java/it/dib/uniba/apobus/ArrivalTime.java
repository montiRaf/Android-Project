package it.dib.uniba.apobus;

public final class ArrivalTime {

    public final String id;
    public final String stopId;
    public final String direction;
    public final String route;
    public final float estimatedTime;

    private ArrivalTime(final String id, final String stopId, final String direction, final String route, final float estimatedTime){
        this.id = id;
        this.stopId = stopId;
        this.direction = direction;
        this.route = route;
        this.estimatedTime = estimatedTime;
    }

    public static class Builder{
        private String mId;
        private String mStopId;
        private String mDirection;
        private String mRoute;
        private float mEstimatedTime;
        private Builder(final String id, final String stopId){    // elementi obbligatori da inizializzare
            this.mId = id;
            this.mStopId = stopId;
        }

        public static Builder create(final String id, final String stopId){
            return new Builder(id, stopId);
        }

        public Builder withDirection(final String direction){
            this.mDirection = direction;
            return this;
        }

        public Builder withRoute(final String route, final float estimatedTime){
            this.mRoute = route;
            this.mEstimatedTime = estimatedTime;
            return this;
        }

        public ArrivalTime build(){
            return new ArrivalTime(mId, mStopId, mDirection, mRoute, mEstimatedTime);
        }
    }
}