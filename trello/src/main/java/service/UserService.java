package service;

import exception.UserDoesNotExist;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private Map<String, User> members;

    public UserService() {
        this.members = new HashMap<>();
    }

    public List<User> getMembers() {
        return new ArrayList<>(members.values());
    }

    public User createMember(String name, String email) {
        User member = new User(name, email);
        members.put(member.getUserId(), member);
        return member;
    }

    // helper
    public boolean isUserExist(String userId) {
        return members.containsKey(userId);
    }

    public User getUserById(String userId) {
        if (!isUserExist(userId)) {
            throw new UserDoesNotExist("User " + userId + " does not exist");
        }
        return members.get(userId);
    }

    public User getUserByEmail(String email) {
        for(String id: members.keySet()) {
            if(members.get(id).getEmail().equals(email)) {
                return members.get(id);
            }
        }

        throw new UserDoesNotExist("User does not exist");
    }
}
