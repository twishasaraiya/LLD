import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    private List<Hotel> hotel;
}

class Hotel {
    private Integer hotelId;
    private String name;
    private String city;
    private double rating;
    private Integer capacity;
}

class Room {
    private Integer roomId;
    private RoomType roomType;
    private Integer hotelId;
    private List<Facility> facilities;
    private Double price;
    private CancellationPolicy cancellationPolicy;

    public Integer getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}

class Facility{
    private String name;
    private String description;
    private String price;
}

class User{
    private String name;
    private UserRole role;
}

enum UserRole{
    ADMIN, STAFF, CUSTOMER
}

enum BookingStatus{
    NOT_PAID, PARTIAL, FULL, EXPIRED, CANCELLED, REFUNDED
}

enum RoomType {
    DELUXE(4, 2), STANDARD(3, 5);

    private int capacity;
    private int maxRooms;

    RoomType(int capacity, int maxRooms) {
        this.capacity = capacity;
        this.maxRooms = maxRooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxRooms() {
        return maxRooms;
    }
}

class Reservation{
    private Integer id;
    private User user;
    private RoomBooking roomBooking;
    private Double price;
    private BookingStatus bookingStatus;
    private Map<String, Object> metadata;

    public Reservation(User user, RoomType roomType, int quantity, Date startDate, Date endDate) {
        this.user = user;
        this.roomBooking = new RoomBooking(roomType, quantity, startDate, endDate)
        this.bookingStatus = BookingStatus.NOT_PAID;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public RoomBooking getRoomBooking() {
        return roomBooking;
    }
}

class RoomBooking{
    private RoomType roomType;
    private List<Integer> roomIds;
    private Date startDate;
    private Date endDate;

    public RoomBooking(RoomType roomType, List<Integer> roomIds, Date startDate, Date endDate) {
        this.roomType = roomType;
        this.roomIds = roomIds;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

class PaymentDetails {
    private String id;
    private String paymentType; // can be an enum too
}

class CancellationPolicy{
    private Integer hotelId;
    private Integer policyId;
    private String rules;
}
