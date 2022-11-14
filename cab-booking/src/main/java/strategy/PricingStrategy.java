package main.java.strategy;

import main.java.models.Location;

public interface PricingStrategy {

    Double findPrice(Location fromLocation, Location toLocation);
}
