package model;

import enums.ColorType;

public class Player {
    private String id;
    private String name;
    private ColorType color;

    public Player(String id, String name, ColorType color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }
}
