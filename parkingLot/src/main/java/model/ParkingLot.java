package model;

import enums.TicketStatus;
import enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private String id;
    private Integer numFloors;
    private List<ParkingFloor> parkingFloors;
    private Map<String, ParkingSpot> spotMap;

    public ParkingLot(String parkingLotId, Integer numFloors, List<ParkingFloor> parkingFloorList) {
        this.id = parkingLotId;
        this.numFloors = numFloors;
        this.parkingFloors = parkingFloorList;
        this.spotMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public static ParkingLot createParkingLot(String parkingLotId, Integer numFloors, Integer numSpotPerFloor){
        List<ParkingFloor> parkingFloors = new ArrayList<>(numFloors);
        for (int i = 1; i <=numFloors; i++) {
            parkingFloors.add(new ParkingFloor(i, numSpotPerFloor));
        }
        ParkingLot parkingLot = new ParkingLot(parkingLotId, numFloors, parkingFloors);
        System.out.println("Created parking lot with " + numFloors + "floors and " +numSpotPerFloor +" slots per floor");
        return parkingLot;
    }

    public Ticket parkVehicle(VehicleType vehicleType, String registrationNum, String color){
        // find slot
        for (ParkingFloor floor:
             parkingFloors) {
            ParkingSpot spot = floor.getNearestAvailableSpot(vehicleType);
            if(spot != null) {
                String ticketId = id + "_" + floor.getFloorNumber() + "_" + spot.getSpotId();
                Vehicle vehicle = new Vehicle(registrationNum, color, vehicleType);
                Ticket ticket = new Ticket(ticketId, TicketStatus.ISSUED, spot.getSpotId());
                spot.updateAvailableStatus(Boolean.FALSE);
                spot.assignVehicle(vehicle);
                spotMap.put(ticketId, spot);
                System.out.println("Parked vehicle. Ticket ID: " + ticketId);
                return ticket;
            }
        }
        throw new RuntimeException("Spot not found");
    }

    public Vehicle unParkVehicle(String ticketId){
        ParkingSpot spot = spotMap.getOrDefault(ticketId, null);
        if(spot == null || spot.getAvailable().equals(Boolean.TRUE)){
            throw new RuntimeException("Invalid ticket");
        }
        spot.updateAvailableStatus(Boolean.TRUE);
        System.out.println("Unparked vehicle with Registration Number: " +  spot.getVehicle().getRegistrationNumber() + " and Color: " + spot.getVehicle().getColor());
        spot.assignVehicle(null);
        return spot.getVehicle();
    }

    public void display(String displayType, VehicleType vehicleType){
        System.out.println("display " + displayType + " " + vehicleType);
        switch (displayType){
            case "free_count":
                for (ParkingFloor floor: parkingFloors) {
                    floor.displayFreeSlotCount(vehicleType);
                }
                break;
            case "free_slots":
                for (ParkingFloor floor: parkingFloors) {
                    floor.displayFreeSlots(vehicleType);
                }
                break;
            case "occupied_slots":
                for (ParkingFloor floor: parkingFloors) {
                    floor.displayOccupiedSlots(vehicleType);
                }
                break;
        }
    }
}

