package service;

import enums.OrderStatus;
import model.Order;

public interface OrderManager {
    OrderStatus getOrderStatus(String orderId);
    OrderStatus updateOrderStatus(String orderId, OrderStatus status);
    Order getOrder(String orderId);
}
