package model;

import enums.DisplayType;
import enums.TicketStatus;
import enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// can be singleton
public class ParkingLot {
    private String id;
    private Integer numFloors;
    private Map<Integer, ParkingFloor> parkingFloors;
    private Map<String, ParkingSpot> issuedTickets;

    private ParkingLot(String parkingLotId, Integer numFloors, Integer numSpotPerFloor) {
        this.id = parkingLotId;
        this.numFloors = numFloors;
        this.parkingFloors = new HashMap<>(numFloors);
        this.issuedTickets = new HashMap<>();
        for (int i = 1; i <=numFloors; i++) {
            parkingFloors.put(i, new ParkingFloor(i, numSpotPerFloor));
        }
    }

    public String getId() {
        return id;
    }


    public static ParkingLot createParkingLot(String parkingLotId, Integer numFloors, Integer numSpotPerFloor){

        ParkingLot parkingLot = new ParkingLot(parkingLotId, numFloors, numSpotPerFloor);
        System.out.println("Created parking lot with " + numFloors + " floors and " +numSpotPerFloor +" slots per floor");
        return parkingLot;
    }

    public Ticket parkVehicle(VehicleType vehicleType, String registrationNum, String color){
        // find slot
        Ticket ticket = null;
        ParkingSpot parkingSpot = findNearestSpotForVehicleType(vehicleType);
        if(parkingSpot == null){
            System.out.println("Parking Lot Full");
        }
        else{
            Vehicle vehicle = new Vehicle(registrationNum, color, vehicleType);
            String ticketId = id+"_"+parkingSpot.getFloorId()+"_"+parkingSpot.getSpotId();
            ticket = new Ticket(ticketId, TicketStatus.ISSUED, parkingSpot.getSpotId());
            issuedTickets.put(ticketId, parkingSpot);

//            parkingSpot.assignVehicle(vehicle);
            parkingFloors.get(parkingSpot.getFloorId()).assignVehicleToSpot(vehicle, parkingSpot);
            System.out.print("Parked vehicle. Ticket ID: " +ticketId);
        }
        return ticket;
    }

    public Vehicle unParkVehicle(String ticketId){
        if(!issuedTickets.containsKey(ticketId)){
            System.out.println("Invalid Ticket");
            return null;
        }
        ParkingSpot parkingSpot = issuedTickets.get(ticketId);
        Vehicle vehicle = parkingSpot.getVehicle();
//        parkingSpot.removeVehicle();
        parkingFloors.get(parkingSpot.getFloorId()).removeVehicleFromSpot(parkingSpot);
        issuedTickets.remove(ticketId);
        // ToDo: Change ticket status to PAID
        System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNumber()+"and Color: "+vehicle.getColor());
        return vehicle;
    }

    public void display(String displayType, String vehicleType){
        for(ParkingFloor parkingFloor: parkingFloors.values()){
            parkingFloor.displayFloorBoard(DisplayType.getDisplayType(displayType), VehicleType.valueOf(vehicleType));
        }
    }

    // can be moved to strategy class
    private ParkingSpot findNearestSpotForVehicleType(VehicleType vehicleType){
        ParkingSpot parkingSpot = null;
        for(int i=1; i<=numFloors; i++){
            if(parkingFloors.get(i).getAvailableSpot(vehicleType) != null){
                parkingSpot = parkingFloors.get(i).getAvailableSpot(vehicleType);
                break;
            }
        }
        return parkingSpot;
    }

 }

