package model;

import constants.ApplicationConstants;
import enums.SpotType;
import enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private Integer floorNumber;
    private Integer numSpots;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(Integer floorNumber, Integer numSpots) {
        this.floorNumber = floorNumber;
        this.numSpots = numSpots;
        this.parkingSpots = createParkingSpots(numSpots);
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public List<ParkingSpot> createParkingSpots(int num){
        List<ParkingSpot> parkingSpotList = new ArrayList<>(numSpots);
        for (int i = 1; i <= num; i++) {
            parkingSpotList.add(ParkingSpotFactory.createSpot(i));  // FACTORY
        }
        return parkingSpotList;
    }

    public ParkingFloor createFloor(int numSpotsPerFloor){
        return new ParkingFloor(1, numSpotsPerFloor);
    }

    public ParkingSpot getNearestAvailableSpot(VehicleType vehicleType){
        for (ParkingSpot spot:
             parkingSpots) {
            if(spot.getAvailable() && spot.getSpotType().toString().equals(vehicleType.toString())){
                return spot;
            }
        }
        return null;
    }
    public void displayFreeSlots(VehicleType vehicleType){
        System.out.println("Free slots for " + vehicleType + " on floor " + floorNumber);
        for (ParkingSpot parkingspot:
             parkingSpots) {
            if(parkingspot.getAvailable() && parkingspot.getSpotType().toString().equals(vehicleType.toString())){
                System.out.print(parkingspot.getSpotId());
            }
        }
    }

    public void displayFreeSlotCount(VehicleType vehicleType){
        int cnt = 0;
        for (ParkingSpot parkingspot:
                parkingSpots) {
//            System.out.println(parkingspot + " : " + parkingspot.getSpotType().toString().equals(vehicleType.toString()));
            if( parkingspot.getAvailable() && parkingspot.getSpotType().toString().equals(vehicleType.toString())){
                cnt++;
            }
        }
        System.out.println("No. of free slots for " + vehicleType +" on Floor " + floorNumber + " : " + cnt);
    }

    public void displayOccupiedSlots(VehicleType vehicleType){
        System.out.println("Occupied slots for " + vehicleType + " on floor " + floorNumber);
        for (ParkingSpot parkingspot:
                parkingSpots) {
            if(!parkingspot.getAvailable() && parkingspot.getSpotType().equals(vehicleType)){
                System.out.print(parkingspot.getSpotId());
            }
        }
    }
}

class ParkingSpotFactory{
    public static ParkingSpot createSpot(int slotId){
        if(ApplicationConstants.spotsForTruck.contains(slotId)){
            return new ParkingSpot(slotId, SpotType.TRUCK, Boolean.TRUE);
        }
        else if(ApplicationConstants.spotsForBike.contains(slotId)){
            return new ParkingSpot(slotId, SpotType.BIKE, Boolean.TRUE);
        }else{
            return new ParkingSpot(slotId, SpotType.CAR, Boolean.TRUE);
        }
    }
}