package model;

import service.ComponentFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<GameComponent> snakes;
    List<GameComponent> ladders;

    List<Player> players;


    public Game() {
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void addGameComponent(Integer head, Integer tail, ComponentType type){
        GameComponent component = new GameComponent(head, tail, type);
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
