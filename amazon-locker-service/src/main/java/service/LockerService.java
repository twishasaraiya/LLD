package service;

import enums.LockerStatus;
import model.Locker;

import java.util.List;

public interface LockerService {
    Locker getLocker(String lockerId);
    Locker newLocker();
    Locker changeLockerStatus(LockerStatus status);
    List<Locker> getLockerByStatus(LockerStatus status);
}
