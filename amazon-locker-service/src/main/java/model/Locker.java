package model;

import enums.LockerStatus;

import static java.util.UUID.randomUUID;

public class Locker {
    private String UUID;
    private LockerStatus status;

    public Locker() {
        this.UUID = randomUUID().toString();
        this.status = LockerStatus.FREE;
    }

    public String getID() {
        return UUID;
    }

    public LockerStatus getStatus() {
        return status;
    }

    public void setStatus(LockerStatus status) {
        this.status = status;
    }
}


