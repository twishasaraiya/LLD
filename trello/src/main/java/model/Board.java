package model;

import enums.Privacy;

import java.util.*;

public class Board {
    private final String id;
    private String name;
    private Privacy privacy;
    private final String url;
    private List<BoardList> lists;
    private List<User> members;

    public Board(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.privacy = Privacy.PUBLIC;
        this.url = "http://board/" + this.id;
        this.lists = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public String getUrl() {
        return url;
    }

    public List<BoardList> getLists() {
        return lists;
    }

    public List<User> getMembers() {
        return members;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public boolean isBoardListExist(BoardList list) {
        return lists.contains(list);
    }

    public void addList(BoardList list) {
        lists.add(list);
    }

    public void removeList(BoardList list) {
        lists.remove(list);
    }

    public boolean isMemberExist(User user) {
        return members.contains(user);
    }

    public void addMember(User member) {
        members.add(member);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", privacy=" + privacy +
                ", url='" + url + '\'' +
                ", lists=" + lists +
                ", members=" + members +
                '}';
    }
}
