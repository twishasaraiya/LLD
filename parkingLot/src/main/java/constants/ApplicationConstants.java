package constants;

import enums.SpotType;
import enums.VehicleType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationConstants {
    public static final List<Integer> spotsForTruck = Arrays.asList(1);
    public static final List<Integer> spotsForBike = Arrays.asList(2,3);

    public static final Map<VehicleType, SpotType> vehicleTypeToSpotType = Map.of(
            VehicleType.BIKE, SpotType.BIKE,
            VehicleType.CAR, SpotType.CAR,
            VehicleType.TRUCK, SpotType.TRUCK);


}
