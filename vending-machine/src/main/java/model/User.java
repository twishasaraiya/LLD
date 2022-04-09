package model;

import enums.UserRole;

public class User {
    private String name;
    private UserRole role;

    public User(String name) {
        this.name = name;
        this.role = UserRole.CUSTOMER;
    }

    public User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }
}
