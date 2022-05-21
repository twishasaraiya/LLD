package main.java.strategy;

import main.java.models.Cab;
import main.java.models.Location;

import java.util.List;

public interface CabFindingStrategy {

    Cab findCab(Location fromLocation, Location toLocation, List<Cab> availableCabs);
}
