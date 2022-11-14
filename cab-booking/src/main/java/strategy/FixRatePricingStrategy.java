package main.java.strategy;

import main.java.models.Location;

public class FixRatePricingStrategy implements PricingStrategy{
    private static final Double PRICE_PER_KM = 30.0;
    @Override
    public Double findPrice(Location fromLocation, Location toLocation) {

        return fromLocation.findDistance(toLocation) * PRICE_PER_KM;
    }
}
