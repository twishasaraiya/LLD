package service.impl;

import model.User;
import service.UserManagement;

import java.util.HashMap;
import java.util.Map;

public class UserManagementImpl implements UserManagement {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void addUser(String userId, String userName, String userEmail, String phoneNumber) {
        if(userMap.containsKey(userId)){
            System.out.println("User Already Exist!");
            return;
        }
        User newUser = new User(userId, userName, userEmail, phoneNumber);
        userMap.putIfAbsent(userId, newUser);
    }

    @Override
    public User getUserById(String userId) {
        if(userMap.containsKey(userId)){
            return userMap.get(userId);
        }
        System.out.println("User not found! ");
        return null;
    }

    @Override
    public String getUserNameById(String userId) {
        if(userMap.containsKey(userId)){
            return userMap.get(userId).getUserName();
        }
        return null;
    }

}
