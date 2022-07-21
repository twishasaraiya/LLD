package model;

import enums.OrderStatus;

import java.util.UUID;

public class Order {
    String id;
    OrderStatus orderStatus;
    Location destination;

    public Order(Location destination) {
        id = UUID.randomUUID().toString();
        orderStatus = OrderStatus.ACCEPTED;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Location getDestination() {
        return destination;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}