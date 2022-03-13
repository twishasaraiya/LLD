package model;

public class Tile {
    int x;
    int y;
    int value;

    public Tile(){
        this.x = 0;
        this.y = 0;
        this.value = 0;
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.value = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
