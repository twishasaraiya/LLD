package model;

public class Player {
    String name;
    Integer numOfAttempts;

    public Player(String name) {
        this.name = name;
        this.numOfAttempts = 0;
    }

    public Integer getNumOfAttempts() {
        return numOfAttempts;
    }

    public void incrementAttempts() {
        this.numOfAttempts++;
    }
}
