package service;

import model.Locker;

import java.util.List;

public class RandomLockerAssignmentStrategy implements LockerAssignmentStrategy{

    @Override
    public Locker getLocker(List<Locker> availableLockers) {
        int randomIdx = (int) (Math.random() * availableLockers.size());
        return availableLockers.get(randomIdx);
    }
}
