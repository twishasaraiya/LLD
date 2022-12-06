package services;

import exceptions.NotFoundException;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {

    private final Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User getUser(String userId){
        if(!users.containsKey(userId)){
            throw new NotFoundException("User not found");
        }
        return users.get(userId);
    }

    public User createUser(String userName){
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, userName);
        users.put(userId, user);
        return user;
    }
}
