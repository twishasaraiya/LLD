package model;

public class Driver extends User{
    boolean isAvailable;
    public Driver(String name) {
        super(name);
        isAvailable = false;

    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
