package model;

import constants.ApplicationConstants;
import enums.DisplayType;
import enums.SpotType;
import enums.VehicleType;
import factory.ParkingSpotFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ParkingFloor {
    private Integer floorId;
    private Integer numSpots;
    private Map<Integer, ParkingSpot> spotMap;
    private Map<SpotType, Set<Integer>> availableSpotsMap;
    private Map<SpotType, Set<Integer>> occupiedSpotsMap;


    public ParkingFloor(Integer floorId, Integer numSpots) {
        this.floorId = floorId;
        this.numSpots = numSpots;
        this.spotMap = new HashMap<>();
        this.availableSpotsMap = new HashMap<>();
        this.occupiedSpotsMap = new HashMap<>();
        for(SpotType spotType: SpotType.values()){
            availableSpotsMap.put(spotType, new TreeSet<>());
            occupiedSpotsMap.put(spotType, new TreeSet<>());
        }
        createParkingSpots();
    }

    private void createParkingSpots(){
        for(int i=1; i<=numSpots; i++){
            ParkingSpot parkingSpot = ParkingSpotFactory.createSpot(i, floorId);
            spotMap.put(parkingSpot.getSpotId(), parkingSpot);
            availableSpotsMap.get(parkingSpot.getSpotType()).add(parkingSpot.getSpotId());
        }
    }

    public void addParkingSot(ParkingSpot parkingSpot){
        spotMap.putIfAbsent(parkingSpot.getSpotId(), parkingSpot);
        if(parkingSpot.isAvailable()){
            availableSpotsMap.get(parkingSpot.getSpotType()).add(parkingSpot.getSpotId());
        }
        else{
            occupiedSpotsMap.get(parkingSpot.getSpotType()).add(parkingSpot.getSpotId());
        }
    }

    public Integer getFloorId() {
        return floorId;
    }


    public ParkingSpot getAvailableSpot(VehicleType vehicleType){
        for(Integer spotId: availableSpotsMap.get(ApplicationConstants.vehicleTypeToSpotType.get(vehicleType))){
            return spotMap.get(spotId);
        }
        return null;
    }

    public ParkingSpot getAvailableSpot(Integer spotId){
        return spotMap.getOrDefault(spotId, null);
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot){
        spot.assignVehicle(vehicle);
        markSpotOccupiedFromAvailable(spot);
    }

    public void removeVehicleFromSpot(ParkingSpot spot){
        spot.removeVehicle();
        markSpotAvailableFromOccupied(spot);
    }

    private void markSpotOccupiedFromAvailable(ParkingSpot parkingSpot){
        // remove from available
        availableSpotsMap.get(parkingSpot.getSpotType()).remove(parkingSpot.getSpotId());
        // add to occupied
        occupiedSpotsMap.get(parkingSpot.getSpotType()).add(parkingSpot.getSpotId());
    }

    private void markSpotAvailableFromOccupied(ParkingSpot parkingSpot){
        // remove from occupied
        occupiedSpotsMap.get(parkingSpot.getSpotType()).remove(parkingSpot.getSpotId());
        // add to available
        availableSpotsMap.get(parkingSpot.getSpotType()).add(parkingSpot.getSpotId());
    }

    public void displayFloorBoard(DisplayType displayType, VehicleType vehicleType){
        switch (displayType){
            case FREE_COUNT:
                System.out.println("No. of free slots for "+vehicleType+" on Floor "+floorId+": "+availableSpotsMap.get(ApplicationConstants.vehicleTypeToSpotType.get(vehicleType)).size());
                break;
            case FREE_SLOTS:
                System.out.println("Free slots for "+ vehicleType+" on Floor "+floorId+": "+availableSpotsMap.get(ApplicationConstants.vehicleTypeToSpotType.get(vehicleType)));
                break;
            case OCCUPIED_SLOTS:
                System.out.println("Occupied slots for "+ vehicleType+" on Floor "+floorId+": "+occupiedSpotsMap.get(ApplicationConstants.vehicleTypeToSpotType.get(vehicleType)));
                break;
            default:
                System.out.println("Invalid displayType!");

        }
    }

}
