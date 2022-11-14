package main.java.strategy;

import main.java.models.Cab;
import main.java.models.Location;

import java.util.List;
import java.util.Optional;

public class NearestCabFindingStrategy implements CabFindingStrategy {

    @Override
    public Cab findCab(Location fromLocation, Location toLocation, List<Cab> availableCabs) {
        if(availableCabs.isEmpty()) return null;
        Optional<Cab> optional = availableCabs.stream()
                .sorted((c1, c2) -> {
                    if(fromLocation.findDistance(c1.getCurrentLocation()) > fromLocation.findDistance(c2.getCurrentLocation())){
                        return 1;
                    }
                    return -1;
                })
                .findFirst();

        return optional.get();
    }

}
