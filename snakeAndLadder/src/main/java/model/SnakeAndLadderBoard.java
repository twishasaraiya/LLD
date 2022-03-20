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

    public void addNewSnake(int head, Snake snake) {
        if(snakes.containsKey(head)) return;
        snakes.put(head, snake);
    }

    public int getSnakeTailPositionByHeadPosition(int headPosition) {
        return snakes.get(headPosition).getTail();
    }

    public boolean hasSnakeAtGivenPosition(int headPosition) {
        return snakes.containsKey(headPosition);
    }

    public Map<Integer, Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Ladder> ladders) {
        this.ladders = ladders;
    }

    public void addNewLadder(int start, Ladder ladder) {
        if(ladders.containsKey(start)) return;
        ladders.put(start, ladder);
    }

    public int getLadderEndPositionByTailPosition(int startPosition) {
        return ladders.get(startPosition).getEnd();
    }

    public boolean hasLadderAtGivenPosition(int startPosition) {
        return ladders.containsKey(startPosition);
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }

    public void addNewPlayer(String playerName, int position) {
        if(playerPieces.containsKey(playerName)) return;
        playerPieces.put(playerName, position);
    }

    public int getCurrentPositionByPlayerName(String playerName) {
        return playerPieces.get(playerName);
    }
}
