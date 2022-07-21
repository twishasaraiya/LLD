package service;

import model.DeliveryExecutor;
import model.Location;

import java.util.List;

public interface DeliveryExecutorSelectionStrategy {
    DeliveryExecutor findDeliveryExecutor(List<DeliveryExecutor> deliveryExecutors, Location destination);
}
