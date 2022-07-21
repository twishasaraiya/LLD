package model;

import java.util.UUID;

public class DeliveryExecutor {
    String id;
    Location location;
    String orderId;
    Boolean isAvailable;

    public DeliveryExecutor(Location location) {
        this.id = UUID.randomUUID().toString();
        this.location = location;
        this.orderId = null;
        this.isAvailable = Boolean.TRUE;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Location getLocation() {
        return location;
    }

    public String getOrderId() {
        return orderId;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
}
