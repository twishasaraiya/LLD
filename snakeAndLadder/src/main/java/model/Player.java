package model;

public class Player {
    private String playerName;
    private Integer currentPosition;
    private boolean isWon;

    public Player(String playerName) {
        this.playerName = playerName;
        this.currentPosition = 0;
        this.isWon = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
