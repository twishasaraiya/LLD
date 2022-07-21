package model;

import enums.TripStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Trip {
    String id;
    TripStatus tripStatus;
    Location src;
    Location dest;
    Double price;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Rider rider;
    Cab cab;

    public static Builder builder(){
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public static class Builder{
        String id;
        TripStatus tripStatus;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Rider rider;
        Cab cab;
        Location src;
        Location dest;
        Double price;

        public Builder setTripStatus(TripStatus tripStatus) {
            this.tripStatus = tripStatus;
            return this;
        }

        public Builder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;

        }

        public Builder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;

        }

        public Builder setRider(Rider rider) {
            this.rider = rider;
            return this;

        }

        public Builder setSrc(Location src) {
            this.src = src;
            return this;
        }

        public Builder setDest(Location dest) {
            this.dest = dest;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setCab(Cab cab) {
            this.cab = cab;
            return this;
        }

        public Trip build(){
            Trip trip = new Trip();
            trip.tripStatus = tripStatus;
            trip.startTime = startTime;
            trip.endTime = endTime;
            trip.cab = cab;
            trip.rider = rider;
            trip.src = src;
            trip.dest = dest;
            trip.price = price;
            return trip;
        }
    }
}
