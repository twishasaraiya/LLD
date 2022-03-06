package controller;

import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private final Map<String, Player> playerMap;
    private Map<String, List<String>> playerToHintListMap;
    private final ScoreboardService scoreboardService;

    public PlayerService() {
        this.playerMap = new HashMap<>();
        this.playerToHintListMap = new HashMap<>();
        scoreboardService = new ScoreboardService();
    }

    public Player newPlayer(String name){
        Player newPlayer = new Player(name);
        playerMap.put(name, newPlayer);
        playerToHintListMap.put(name, new ArrayList<>());
        return newPlayer;
    }

    public Player findPlayer(String playerName){
        Player player = playerMap.get(playerName);
        if(player == null) throw new IllegalArgumentException("Player not found for player name = " + playerName);
        return player;
    }

    public Integer getNumberOfAttemptsForPlayer(String playerName){
        return findPlayer(playerName).getNumOfAttempts();
    }

    public void increaseAttemptsForPlayer(String playerName){
        findPlayer(playerName).incrementAttempts();
    }

    public void addHintForPlayer(String playerName, String hint){
        playerToHintListMap.get(findPlayer(playerName).getName()).add(hint);
    }

    public void displayHintForPlayer(String playerName){
        for (String hint:
             playerToHintListMap.get(findPlayer(playerName).getName())) {
            System.out.println(hint);
        }
    }

    public Integer incrementCurrentScoreForPlayer(String playerName, Integer newPoints){
        Player player = findPlayer(playerName);

        removeOldScoresForPlayer(player);

        player.incrementCurrentScore(newPoints);
        player.incrementAllTimeScore();

        updateScoreboardsForPlayer(player);

        return player.getCurrScore();
    }

    private void removeOldScoresForPlayer(Player player){
        scoreboardService.removeOldCurrentScore(player);
        scoreboardService.removeOldAllTimeScore(player);
    }

    private void updateScoreboardsForPlayer(Player player){
        scoreboardService.updatePlayerCurrentRanking(player);
        scoreboardService.updatePlayerAllTimeRanking(player);
    }

    public void resetRound(){
        for (Player player:
             playerMap.values()) {
            player.resetCurrentScore();
            player.resetAttempts();
        }
        resetPlayerHints();
        scoreboardService.resetCurrentRanking();
    }

    private void resetPlayerHints(){
        for (String playerName:
             playerToHintListMap.keySet()) {
            playerToHintListMap.put(playerName, new ArrayList<>());
        }
    }
    public void displayScoreboard(String type){
        scoreboardService.displayScoreCard(type);
    }
}
