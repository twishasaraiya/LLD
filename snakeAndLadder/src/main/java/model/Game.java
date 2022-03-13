package model;

import service.ComponentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<GameComponent> snakes;
    private List<GameComponent> ladders;
    private List<Player> players;
    private Map<Player, Position> playerToPositionMap;
    private static final Integer DEFAULT_BOARD_SIZE = 100;
    private static Integer BOARD_SIZE;

    public Game() {
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        players = new ArrayList<>();
        playerToPositionMap = new HashMap<>();
        this.BOARD_SIZE = DEFAULT_BOARD_SIZE;
    }

    public Game(Integer boardSize){
        this();
        this.BOARD_SIZE = boardSize;
    }

    public static Integer getBoardSize() {
        return BOARD_SIZE;
    }

    public Position getPlayerPosition(Player player){
        return this.playerToPositionMap.get(player);
    }

    public void addPlayerPosition(Player player){
        this.playerToPositionMap.put(player, new Position());
    }

    public void addGameComponent(Integer head, Integer tail, ComponentType type){
        GameComponent component = ComponentFactory.buildComponent(head, tail, type);
        switch (type){
            case LADDER:
                this.ladders.add(component);
                break;
            case SNAKE:
                this.snakes.add(component);
                break;
        }
    }

    public void addPlayer(String name){
        Player player = new Player(name);
        players.add(player);
    }

    public List<GameComponent> getSnakes() {
        return snakes;
    }

    public List<GameComponent> getLadders() {
        return ladders;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
