package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private String groupId;
    private String groupName;
    private List<User> members;

    public Group() {
        this.groupId = UUID.randomUUID().toString();
        this.members = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void addMember(User user){
        members.add(user);
    }

    public void removeMember(User user){

    }

    public int getMembersCount(){
        return members.size();
    }
}
