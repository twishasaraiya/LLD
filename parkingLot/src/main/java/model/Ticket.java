package model;

import enums.TicketStatus;

public class Ticket {
    private String ticketId; // parkingLot_floor_spot
    private TicketStatus ticketStatus;
    private Integer spotId;

    public Ticket(String ticketId, TicketStatus ticketStatus, Integer spotId) {
        this.ticketId = ticketId;
        this.ticketStatus = ticketStatus;
        this.spotId = spotId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void updateTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
