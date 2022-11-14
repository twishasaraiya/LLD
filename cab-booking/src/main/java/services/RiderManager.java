package main.java.services;

import main.java.models.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {

    private final Map<String, Rider> riderMap;

    public RiderManager() {
        this.riderMap = new HashMap<>();
    }

    public Rider createRider(String id, String name){
        Rider rider  = new Rider(id, name);
        riderMap.putIfAbsent(id, rider);
        return rider;
    }

    public Rider getRiderById(String id){
        if(!riderMap.containsKey(id)){
            return null;
        }

        return riderMap.get(id);
    }
}
