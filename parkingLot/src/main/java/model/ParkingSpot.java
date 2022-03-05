package model;

import enums.SpotType;

public class ParkingSpot {
    private Integer spotId;
    private Integer floorId;
    private SpotType spotType;
    private Boolean availableStatus;
    private Vehicle vehicle;

    public ParkingSpot(Integer spotId, Integer floorId, SpotType spotType, Boolean availableStatus) {
        this.spotId = spotId;
        this.floorId = floorId;
        this.spotType = spotType;
        this.availableStatus = availableStatus;
        this.vehicle = null;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Boolean isAvailable() {
        return availableStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public synchronized void assignVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.availableStatus = Boolean.FALSE;
    }

    public void removeVehicle(){
        this.vehicle = null;
        this.availableStatus = Boolean.TRUE;
    }




}
