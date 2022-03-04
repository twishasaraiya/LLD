package factory;

import constants.ApplicationConstants;
import enums.SpotType;
import model.ParkingSpot;

public class ParkingSpotFactory {
    public static ParkingSpot createSpot(int slotId, int floorId){
        if(ApplicationConstants.spotsForTruck.contains(slotId)){
            return new ParkingSpot(slotId, floorId, SpotType.TRUCK, Boolean.TRUE);
        }
        else if(ApplicationConstants.spotsForBike.contains(slotId)){
            return new ParkingSpot(slotId, floorId, SpotType.BIKE, Boolean.TRUE);
        }else{
            return new ParkingSpot(slotId, floorId, SpotType.CAR, Boolean.TRUE);
        }
    }
}
