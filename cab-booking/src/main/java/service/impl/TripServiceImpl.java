package service.impl;

import enums.TripStatus;
import model.Cab;
import model.Driver;
import model.Location;
import model.Rider;
import model.Trip;
import service.CabManager;
import service.CabSelectionStrategy;
import service.TripService;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class TripServiceImpl implements TripService {

    private CabSelectionStrategy cabSelectionStrategy;
    private Map<String, Trip> tripMap;
    private CabManager cabManager;

    public TripServiceImpl(CabSelectionStrategy cabSelectionStrategy, CabManager cabManager) {
        this.cabSelectionStrategy = cabSelectionStrategy;
        this.tripMap = new HashMap<>();
        this.cabManager = cabManager;
    }

    @Override
    public String bookCab(Rider rider, int xSrclocation, int ySrcLocation, int xDestlocation, int yDestlocation) {
        Location src = new Location(xSrclocation, ySrcLocation);
        Location dest = new Location(xDestlocation, yDestlocation);

        List<Cab> availableCabs = cabManager.getAllAvailableCabs();
        Cab cab = cabSelectionStrategy.findCab(availableCabs, src).orElseThrow(() -> new NoSuchElementException("Cab not found. No available cabs in your region currently"));
        Trip trip = Trip.builder()
                .setCab(cab)
                .setRider(rider)
                .setTripStatus(TripStatus.INPROGRESS)
                .setStartTime(LocalDateTime.now())
                .build();

        return trip.getId();
    }

    @Override
    public TripStatus endTrip(String tripId) {
        if(!tripMap.containsKey(tripId)){
            throw new InvalidParameterException("Invalid trip");
        }

        Trip trip = tripMap.get(tripId);
        trip.setEndTime(LocalDateTime.now());
        trip.setTripStatus(TripStatus.FINISHED);
        return trip.getTripStatus();
    }
}
