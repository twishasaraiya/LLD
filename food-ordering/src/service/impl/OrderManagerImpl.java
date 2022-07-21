package service.impl;

import enums.OrderStatus;
import model.Location;
import model.Order;
import service.OrderManager;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class OrderManagerImpl implements OrderManager {
    Map<String, Order> orderMap;

    public OrderManagerImpl() {
        this.orderMap = new HashMap<>();
    }

    @Override
    public OrderStatus getOrderStatus(String orderId) {
        if (!orderMap.containsKey(orderId)) {
            throw new InvalidParameterException("Invalid order. No data found for orderId " + orderId);
        }
        return orderMap.get(orderId).getOrderStatus();
    }

    @Override
    public OrderStatus updateOrderStatus(String orderId, OrderStatus status) {
        if (!orderMap.containsKey(orderId)) {
            throw new InvalidParameterException("Invalid order. No data found for orderId " + orderId);
        }
        orderMap.get(orderId).setOrderStatus(status);
        return status;
    }

    public Order getOrder(String orderId){
        if (!orderMap.containsKey(orderId)) {
            throw new InvalidParameterException("Invalid order. No data found for orderId " + orderId);
        }
        return orderMap.get(orderId);
    }
}
