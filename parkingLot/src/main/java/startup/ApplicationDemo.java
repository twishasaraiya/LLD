package startup;

import enums.VehicleType;
import model.ParkingLot;
import model.Ticket;
import model.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://leetcode.com/discuss/interview-question/object-oriented-design/1749860/Parking-Lot-Design-or-workattech-or-Review-Appreciated
 * TODO
 * 1. Apply different design pattern
 * 2. Thread safe??
 */

/**
 * Command pattern here
 */
public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ParkingLot parkingLot = null;

        while (true) {
            String[] cmd = br.readLine().split(" ");
            System.out.println(cmd);
            switch (cmd[0]) {
                case "create_parking_lot":
                    parkingLot = ParkingLot.createParkingLot(cmd[1], Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
                    break;
                case "park_vehicle":
                    parkingLot.parkVehicle(VehicleType.valueOf(cmd[1]), cmd[2], cmd[3]);
                    break;
                case "unpark_vehicle":
                    parkingLot.unParkVehicle(cmd[1]);
                    break;
                case "display":
                    parkingLot.display(cmd[1], cmd[2]);
                    break;
                case "exit":
                    return;
            }
        }
    }
}
