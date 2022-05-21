package main.java.models;

import main.java.enums.TripStatus;

import static main.java.enums.TripStatus.COMPLETED;
import static main.java.enums.TripStatus.IN_PROGRESS;

public class Trip {

    private final String id;
    private final Rider rider;
    private final Cab cab;
    private final Location fromLocation;
    private final Location toLocation;

    private final Double price;
    private TripStatus tripStatus;


    public Trip(String id, Rider rider, Cab cab, Location fromLocation, Location toLocation, Double price) {
        this.id = id;
        this.rider = rider;
        this.cab = cab;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.tripStatus = IN_PROGRESS;
    }

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Cab getCab() {
        return cab;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void completeTrip(){
        this.tripStatus = COMPLETED;
    }
}
