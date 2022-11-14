package main.java.services;


import main.java.exceptions.CabNotFoundException;
import main.java.models.Cab;
import main.java.models.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabManager {
    private final Map<String, Cab> cabMap;

    public CabManager() {
        this.cabMap = new HashMap<>();

    }

    public Cab createCab(String id, String regNumber, String driverName, Location currentLocation){
        if(cabMap.containsKey(id)) return cabMap.get(id);
        Cab cab  = new Cab(id, regNumber, driverName, currentLocation, Boolean.TRUE);
        cabMap.put(id, cab);
        return cab;
    }


    public Cab findById(String id){
        if(!cabMap.containsKey(id)){
            throw new CabNotFoundException();
        }
        return cabMap.get(id);
    }

    public List<Cab> getAvailableCabs(){
        List<Cab> availableCabs = new ArrayList<>();
        for(Cab cab: cabMap.values()){
            if(cab.isAvailable()) availableCabs.add(cab);
        }
        return  availableCabs;
    }
}
