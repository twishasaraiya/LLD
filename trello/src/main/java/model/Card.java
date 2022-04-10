package model;

import java.util.UUID;

public class Card {
    private final String id;
    private String name;
    private String description;
    private User assignedUser;
    private String tag;

    public Card(String name) {
        this(name, "");
    }

    public Card(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.assignedUser = null;
        this.tag = this.id + "_" + this.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public String getTag() {
        return tag;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void assignUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void unAssignUser() {
        this.assignedUser = null;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", assignedUser=" + assignedUser +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public Card clone() {
        return new Card(this.name);
    }
}
