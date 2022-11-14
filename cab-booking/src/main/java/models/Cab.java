package main.java.models;

public class Cab {

    private final String id;
    private final String regNumber;
    private final String driverName;

    private Location currentLocation;

    private boolean isAvailable;


    public Cab(String id, String regNumber, String driverName, Location currentLocation, boolean isAvailable) {
        this.id = id;
        this.regNumber = regNumber;
        this.driverName = driverName;
        this.currentLocation = currentLocation;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public void updateLocation(Location location){
        this.currentLocation = location;
    }

    public void updateAvailability(){
        this.isAvailable = !isAvailable;
    }
}
