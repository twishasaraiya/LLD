import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

interface HotelBookingSystem {
    List<Hotel> searchByName(String name);
    List<Hotel> searchByLocation(String cityName);
    List<Hotel> searchByRating(String rating);

    BookingStatus bookRoom(User user, RoomType roomType);

}
public class Service {
    private SearchService searchService;
}

class SearchService {
    private Map<String, List<Hotel>> mapHotelToName;
    private Map<String, List<Hotel>> mapHotelToCity;
    private Map<Double, List<Hotel>> mapHotelToRating;
}

class RoomService {

    Map<RoomType, List<Reservation>> roomTypeBookedRoomsMap;

    public RoomService() {
        for (RoomType roomtype:
             RoomType.values()) {
            roomTypeBookedRoomsMap.put(roomtype, new ArrayList<>());
        }
    }

    BookingStatus bookRoom(User user, RoomType roomType, int quantity, Date startDate, Date endDate){
        int maxRooms = roomType.getMaxRooms();

        int reservedRooms = getReservedRoomsCountByRoomType(roomType, startDate, endDate);

        int availableRooms = maxRooms - reservedRooms;

        if(availableRooms < quantity){
            // throw RoomNotAvailableException
        }

        Reservation reservation = new Reservation(user,roomType, quantity, startDate, endDate);

        roomTypeBookedRoomsMap.get(roomType).add(reservation);

        return reservation.getBookingStatus();
    }

    int getReservedRoomsCountByRoomType(RoomType roomType, Date startDate, Date endDate){
        return (int) roomTypeBookedRoomsMap.get(roomType).stream()
                .filter(reservation -> reservation.getRoomBooking().getStartDate().equals(startDate))
                .filter(reservation -> reservation.getRoomBooking().getEndDate().equals(endDate))
                .count();
    }
}