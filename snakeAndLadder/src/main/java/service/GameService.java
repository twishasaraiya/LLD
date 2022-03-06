package service;

import model.ComponentType;
import model.Game;
import model.GameComponent;
import model.Player;
import model.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mapAllPlayerToStartPosition(this.game.getPlayers());
        Player currPlayer = null;
        do{
            currPlayer = getPlayer(0);
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

        // check if there is a snake head at this new position
        for (GameComponent snake:
             this.game.getSnakes()) {
            if(snake.getHead() == estimatedPosition){
                estimatedPosition = snake.getTail();
                break;
            }
        }

        // check if there is ladder at estimated position
        for (GameComponent ladder:
                game.getLadders()) {
            if(ladder.getTail() == estimatedPosition){
                estimatedPosition = ladder.getHead();
            }
        }

        return estimatedPosition;
    }

    private Player getPlayer(Integer idx){
        if(idx == null || idx >= this.game.getPlayers().size()){
            idx = 0;
        }
        return this.game.getPlayers().get(idx);
    }
    public Integer throwDice(){
        return (int) (Math.random() * DICE_SIZE + 1);
    }

    private void mapAllPlayerToStartPosition(List<Player> players){
        for (Player player:
             players) {
            this.playerToPositionMap.put(player, new Position());
        }
    }

    private Boolean checkIfPlayerWonGame(Player player){
        return this.playerToPositionMap.get(player).getPositionId() == 100;
    }
}
