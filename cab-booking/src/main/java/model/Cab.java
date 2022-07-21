package model;

import java.util.UUID;

public class Cab {
    // CabType
    String id;
    Location location;
    Driver driver;

    public Cab(Location location, Driver driver) {
        this.id = UUID.randomUUID().toString();
        this.location = location;
        this.driver = driver;
    }

    public String getId() {
        return id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getLocation() {
        return location;
    }
}
