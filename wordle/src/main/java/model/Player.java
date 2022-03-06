package model;

public class Player implements Comparable<Player> {
    String name;
    Integer numOfAttempts;
    Integer currScore;
    Integer allTimeScore;

    public Player(String name) {
        this.name = name;
        this.numOfAttempts = 0;
        this.currScore = 0;
        this.allTimeScore = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getNumOfAttempts() {
        return numOfAttempts;
    }

    public void incrementAttempts() {
        this.numOfAttempts++;
    }

    public void incrementCurrentScore(Integer newPoints){
        currScore += newPoints;
    }

    public Integer getCurrScore() {
        return currScore;
    }

    public void incrementAllTimeScore(){
        this.allTimeScore += this.currScore;
    }

    public void resetCurrentScore(){
        this.currScore = 0;
    }

    public void resetAttempts(){
        this.numOfAttempts = 0;
    }

    public Integer getAllTimeScore() {
        return allTimeScore;
    }

    @Override
    public int compareTo(Player o) {
        return this.getName().compareTo(o.getName());
    }
}
