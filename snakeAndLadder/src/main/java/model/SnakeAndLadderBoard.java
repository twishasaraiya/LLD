package model;

import java.util.HashMap;
import java.util.Map;

public class SnakeAndLadderBoard {
    private int size;
    private Map<Integer, Snake> snakes;
    private Map<Integer, Ladder> ladders;
    private Map<String, Integer> playerPieces;

    public SnakeAndLadderBoard(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.playerPieces = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public Map<Integer, Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(Map<Integer, Snake> snakes) {
        this.snakes = snakes;
    }

    public Map<Integer, Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Ladder> ladders) {
        this.ladders = ladders;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
