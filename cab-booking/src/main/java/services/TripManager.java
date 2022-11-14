package main.java.services;

import main.java.exceptions.CabNotAvailableException;
import main.java.exceptions.TripHistoryNotFoundException;
import main.java.exceptions.TripNotFoundException;
import main.java.models.Cab;
import main.java.models.Location;
import main.java.models.Rider;
import main.java.models.Trip;
import main.java.strategy.CabFindingStrategy;
import main.java.strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager {
    private final Map<String, Trip> tripMap;
    private final Map<String, List<Trip>> riderTripMap;
    private final CabManager cabManager;
    private final CabFindingStrategy cabFindingStrategy;
    private final PricingStrategy pricingStrategy;

    public TripManager(CabFindingStrategy cabFindingStrategy, PricingStrategy pricingStrategy) {
        this.cabFindingStrategy = cabFindingStrategy;
        this.pricingStrategy = pricingStrategy;
        this.tripMap = new HashMap<>();
        this.riderTripMap = new HashMap<>();
        this.cabManager = new CabManager();
    }

    public Trip creteTrip(String id, Rider rider, Location fromLocation, Location toLocation){
        Cab cab = cabFindingStrategy.findCab(fromLocation, toLocation, cabManager.getAvailableCabs());
        if(cab == null){
            throw new CabNotAvailableException();
        }
        Double price = pricingStrategy.findPrice(fromLocation, toLocation);
        Trip trip = new Trip(id, rider, cab, fromLocation, toLocation, price);
        tripMap.put(id, trip);
        if(!riderTripMap.containsKey(rider.getId())){
            riderTripMap.put(rider.getId(), new ArrayList<>());
        }
        riderTripMap.get(rider.getId()).add(trip);
        return trip;
    }


    public void completeTrip(String tripId){
        tripMap.get(tripId).completeTrip();
    }

    public Trip getTripById(String tripId){
        if(!tripMap.containsKey(tripId)){
            throw new TripNotFoundException();
        }
        return tripMap.get(tripId);
    }

    public List<Trip> getTripHistory(String riderId){
        if(!riderTripMap.containsKey(riderId)){
            throw new TripHistoryNotFoundException();
        }
        return riderTripMap.get(riderId);
    }
}




