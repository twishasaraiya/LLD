package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {
    private int boardSize;
    private boolean isPlayWith2Dice;
    private List<Player> playerList;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Dice dice;

    public SnakeAndLadderBoard(int boardSize) {
        this.boardSize = boardSize;
        this.playerList = new ArrayList<>();
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.dice = new Dice();
    }

    public void setPlayWith2Dice(boolean playWith2Dice) {
        isPlayWith2Dice = playWith2Dice;
    }

    public void addSnakePosition(int headPosition, int tailPosition) {
        snakes.put(headPosition, tailPosition);
    }

    public void addLadderPosition(int startPosition, int endPosition) {
        ladders.put(startPosition, endPosition);
    }

    public void addPlayer(String playerName) {
        playerList.add(new Player(playerName));
    }

    public void playGame() {
        int totalPlayersLeft = playerList.size();

        if(isPlayWith2Dice) {
            System.out.println("Playing with 2 dice");
        } else {
            System.out.println("Playing with 1 dice");
        }

        while(totalPlayersLeft > 1) {
            for(Player player: playerList) {
                if(!player.isWon()) {
                    int diceValue = dice.rollDice();
                    if(isPlayWith2Dice) {
                        diceValue += dice.rollDice();
                    }

                    // check for consecutive 6's
                    if(diceValue == 6) {
                        int val = dice.rollDice();

                        if(val == 6) val += dice.rollDice(); // second consecutive 6

                        if(val != 12) diceValue += val; // third consecutive 6
                        else {
                            System.out.println("Skipping " + player.getPlayerName() + " move because of three consecutive 6");
                            continue; //skip the current player move
                        }
                    }

                    int currentPosition = player.getCurrentPosition();
                    int finalPosition = player.getCurrentPosition() + diceValue;


                    // If final position > boardSize then do not move
                    if(finalPosition > boardSize) continue;

                    // If final position has snake head then move to the tail and update the final position
                    if(snakes.containsKey(finalPosition)) {
                        finalPosition = snakes.get(finalPosition);
                    }

                    // If final position has ladder start position then move to the end position and update the final position
                    if(ladders.containsKey(finalPosition)) {
                        finalPosition = ladders.get(finalPosition);
                    }

                    // set player's current position
                    player.setCurrentPosition(finalPosition);

                    // check if player has won the game
                    if(player.getCurrentPosition() == boardSize) {
                        player.setWon(true);
                        totalPlayersLeft--;
                        System.out.println(player.getPlayerName() + " wins the game");
                    } else {
                        System.out.println(player.getPlayerName() + " rolled a " + diceValue + " and moved from " + currentPosition + " " + finalPosition);
                    }
                }
            }
        }
    }

}
