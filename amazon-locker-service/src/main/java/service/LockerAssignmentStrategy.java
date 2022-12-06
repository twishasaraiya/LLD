package service;

import model.Locker;

import java.util.List;

public interface LockerAssignmentStrategy {
    Locker getLocker(List<Locker> availableLockers);
}
