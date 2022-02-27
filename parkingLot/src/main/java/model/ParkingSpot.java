package model;

import enums.SpotType;

public class ParkingSpot {
    private Integer spotId;
    private SpotType spotType;
    private Boolean isAvailable;
    private Vehicle vehicle;

    public ParkingSpot(Integer spotId, SpotType spotType, Boolean isAvailable) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isAvailable = isAvailable;
        this.vehicle = null;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void updateAvailableStatus(Boolean available) {
        isAvailable = available;
    }

    public void assignVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


}
