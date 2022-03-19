package service;

import model.User;

public interface UserManagement {

    void addUser(String userId, String userName, String userEmail, String phoneNumber);

    User getUserById(String userId);

    String getUserNameById(String userId);
}
