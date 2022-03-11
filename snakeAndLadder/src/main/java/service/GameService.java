package service;

import model.ComponentType;
import model.Game;
import model.GameComponent;
import model.Player;
import model.Position;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GameService {

    private final Game game;
    private Map<Player, Position> playerToPositionMap;
    private static final Integer DICE_SIZE = 6;

    private GameService(Game game) {
        this.game = game;
        playerToPositionMap = new HashMap<>();
    }

    public static GameService newGame(){
        return new GameService(new Game());
    }

    public void addGameComponent(Integer head, Integer tail, ComponentType type){
        this.game.addGameComponent(head, tail, type);
    }

    public void addPlayer(String name){
        this.game.addPlayer(name);
    }

    public void startGame(){
        Queue<Player> playerQueue = mapAllPlayerToStartPosition(this.game.getPlayers());
        Player currPlayer = null;
        do{
            currPlayer = getPlayer(playerQueue);
            if(currPlayer == null) return;
            Integer diceOutput = throwDice();
            Integer newPosition = calculateNewPositionForPlayer(currPlayer, diceOutput);
            System.out.println(currPlayer.getName() + " rolled a " + diceOutput + " and moved from " + this.playerToPositionMap.get(currPlayer).getPositionId() + " to " + newPosition);
            updatePlayerPostion(currPlayer, newPosition);
        }while(!checkIfPlayerWonGame(currPlayer));

        System.out.println(currPlayer.getName() + " wins the game");
    }

    private void updatePlayerPostion(Player player, Integer newPosition){
        this.playerToPositionMap.get(player).setPositionId(newPosition);
    }

    private Integer calculateNewPositionForPlayer(Player player, Integer diceOutput){
        Integer estimatedPosition = this.playerToPositionMap.get(player).getPositionId() + diceOutput;
        if(estimatedPosition > 100){
            return this.playerToPositionMap.get(player).getPositionId();
        }

        // check if there is a game component at this new position
        GameComponent gameComponentAtPosition = getGameComponentAtPostion(estimatedPosition);
        while (gameComponentAtPosition != null){
            estimatedPosition = gameComponentAtPosition.getTail();
            gameComponentAtPosition = getGameComponentAtPostion(estimatedPosition);
        }

        return estimatedPosition;
    }

    private GameComponent getGameComponentAtPostion(Integer position){

        // MAP can be used here for faster retrieval
        GameComponent component = this.game.getSnakes()
                .stream()
                .filter(gameComponent -> gameComponent.getHead() == position)
                .findFirst()
                .orElse(null);

        if (component == null){
            component = this.game.getLadders()
                    .stream()
                    .filter(gameComponent -> gameComponent.getHead() == position)
                    .findFirst()
                    .orElse(null);
        }

        return component;
    }

    private Player getPlayer(Queue<Player> playerQueue){
        if(playerQueue.isEmpty()){
            return null;
        }
        Player currPlayer = playerQueue.poll();
        playerQueue.add(currPlayer);
        return currPlayer;
    }
    public Integer throwDice(){
        return (int) (Math.random() * DICE_SIZE + 1);
    }

    private Queue<Player> mapAllPlayerToStartPosition(List<Player> players){
        Queue<Player> playerQueue = new LinkedList<>();
        for (Player player:
             players) {
            this.playerToPositionMap.put(player, new Position());
            playerQueue.add(player);
        }
        return playerQueue;
    }

    private Boolean checkIfPlayerWonGame(Player player){
        return this.playerToPositionMap.get(player).getPositionId() == 100;
    }
}
