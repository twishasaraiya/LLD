package main.java.models;

public class Location {

    private final double x;
    private final double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double findDistance(Location obj){
        return Math.sqrt((this.x - obj.x) * (this.x - obj.x) + (this.y - obj.y) * (this.y - obj.y));
    }
}
