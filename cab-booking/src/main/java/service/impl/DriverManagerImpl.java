package service.impl;

import model.Cab;
import model.Driver;
import model.Location;
import service.DriverManager;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class DriverManagerImpl implements DriverManager {

    Map<String, Driver> driverMap;

    public DriverManagerImpl() {
        this.driverMap = new HashMap<>();
    }

    @Override
    public String registerDriver(String name,Location initialLocation) {
        Driver driver = new Driver(name);
        driverMap.put(driver.getId(), driver);
        return driver.getId();
    }


}
