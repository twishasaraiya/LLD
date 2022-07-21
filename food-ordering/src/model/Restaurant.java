package model;

import java.util.Queue;
import java.util.UUID;

public class Restaurant {
    String id;
    Queue<Order> orderQueue;

    public Restaurant(Queue<Order> orderQueue) {
        this.id = UUID.randomUUID().toString();
        this.orderQueue = orderQueue;
    }
}
