package service;

import model.Cab;
import model.Driver;
import model.Location;

import java.util.List;

public interface CabManager {
    Cab addNewCab(Location location, Driver driver);
    void updateDriverLocation(String driverId, Location location);
    List<Cab> getAllAvailableCabs();
}
