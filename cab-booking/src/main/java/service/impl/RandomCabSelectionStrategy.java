package service.impl;

import model.Cab;
import model.Location;
import service.CabSelectionStrategy;

import java.util.List;
import java.util.Optional;

public class RandomCabSelectionStrategy implements CabSelectionStrategy {

    private final Integer MAX_RADIUS = 20;
    @Override
    public Optional<Cab> findCab(List<Cab> availableCabs, Location srcLocation) {
        return availableCabs.stream().filter(cab -> {
            if(calculateDistance(cab.getLocation(), srcLocation) <= MAX_RADIUS) return true;
            return false;
        })
                .findAny();
    }


    private double calculateDistance(Location s, Location d){
        return Math.sqrt(Math.pow(s.x - d.x, 2) + Math.pow(s.y-d.y, 2));
    }
}
