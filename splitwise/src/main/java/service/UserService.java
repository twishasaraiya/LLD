package service;

import model.User;

public class UserService {

    public void addUser(Long id, String name, String email){
        new User(id, name, email);
    }
}
