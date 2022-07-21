package service.impl;

import model.Rider;
import service.UserManager;

public class UserManagerImpl implements UserManager {
    @Override
    public String registerRider(String name) {
        Rider rider = new Rider(name);
        return rider.getId();
    }
}
