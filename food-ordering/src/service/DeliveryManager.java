package service;

import model.DeliveryExecutor;

public interface DeliveryManager {
    DeliveryExecutor assignDeliverExecutor(String orderId);
}
