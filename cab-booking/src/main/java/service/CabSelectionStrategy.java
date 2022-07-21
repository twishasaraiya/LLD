package service;

import model.Cab;
import model.Location;

import java.util.List;
import java.util.Optional;

public interface CabSelectionStrategy {
    Optional<Cab> findCab(List<Cab> availableCabs, Location srcLocation);
}
