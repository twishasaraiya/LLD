package model;

import java.util.UUID;

public class Player {
    private String playerName;
    private String id;

    public Player(String playerName) {
        this.playerName = playerName;
        this.id = UUID.randomUUID().toString();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getId() {
        return id;
    }
}
