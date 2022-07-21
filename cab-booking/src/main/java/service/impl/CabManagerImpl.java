package service.impl;

import model.Cab;
import model.Driver;
import model.Location;
import service.CabManager;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CabManagerImpl implements CabManager {
    Map<String, Cab> cabManagerMap;

    public CabManagerImpl() {
        this.cabManagerMap = new HashMap<>();
    }

    @Override
    public Cab addNewCab(Location location, Driver driver) {
        Cab cab = new Cab(location,driver);
        cabManagerMap.put(cab.getId(), cab);
        return cab;
    }

    @Override
    public void updateDriverLocation(String cabId, Location location) {
        if(!cabManagerMap.containsKey(cabId)){
            throw new InvalidParameterException("Invalid driver");
        }

        cabManagerMap.get(cabId).setLocation(location);
    }

    @Override
    public List<Cab> getAllAvailableCabs() {
        return cabManagerMap.values().stream()
                .filter(cab -> cab.getDriver().isAvailable())
                .collect(Collectors.toList());
    }
}
