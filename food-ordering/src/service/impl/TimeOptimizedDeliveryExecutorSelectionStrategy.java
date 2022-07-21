package service.impl;

import model.DeliveryExecutor;
import model.Location;
import service.DeliveryExecutorSelectionStrategy;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class TimeOptimizedDeliveryExecutorSelectionStrategy implements DeliveryExecutorSelectionStrategy {
    private final int CONSTANT_SPEED = 10;

    @Override
    public DeliveryExecutor findDeliveryExecutor(List<DeliveryExecutor> deliveryExecutors, Location destination) {
        return deliveryExecutors
                .stream()
                .map(de -> {
                    int time = calculateEstimatedTimeToDeliver(de.getLocation(), destination);
                    return new AbstractMap.SimpleEntry<>(de, time);
                })
                .sorted(Comparator.comparing(e -> e.getValue()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No Delivery Executor found"))
                .getKey();
    }

    private int calculateEstimatedTimeToDeliver(Location src, Location destination){
        double distance = Math.sqrt(Math.pow(src.x - destination.x, 2) + Math.pow(src.y - destination.y,2));
        return (int) Math.ceil(distance / CONSTANT_SPEED);
    }
}
