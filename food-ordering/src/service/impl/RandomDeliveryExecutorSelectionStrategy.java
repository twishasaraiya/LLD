package service.impl;

import model.DeliveryExecutor;
import model.Location;
import service.DeliveryExecutorSelectionStrategy;

import java.util.List;

public class RandomDeliveryExecutorSelectionStrategy implements DeliveryExecutorSelectionStrategy {
    @Override
    public DeliveryExecutor findDeliveryExecutor(List<DeliveryExecutor> deliveryExecutors, Location destination) {
        return null;
    }
}
