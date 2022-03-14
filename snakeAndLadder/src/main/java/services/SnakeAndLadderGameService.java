package services;

import model.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SnakeAndLadderGameService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Queue<Player> players;

    private int noOfDices;

    private static final int DEFAULT_BOARD_SIZE = 100; //The board will have 100 cells numbered from 1 to 100.
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadderGameService() {
        this(SnakeAndLadderGameService.DEFAULT_BOARD_SIZE);
    }

    public SnakeAndLadderGameService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<>();
        noOfDices = SnakeAndLadderGameService.DEFAULT_NO_OF_DICES;
    }

    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void addSnakePosition(int headPosition, int tailPosition) {
        Map<Integer, Snake> snakes = snakeAndLadderBoard.getSnakes();
        snakes.put(headPosition, new Snake(headPosition, tailPosition));
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void addLadderPosition(int startPosition, int endPosition) {
        Map<Integer, Ladder> ladders = snakeAndLadderBoard.getLadders();
        ladders.put(startPosition, new Ladder(startPosition, endPosition));
        snakeAndLadderBoard.setLadders(ladders);
    }

    public void addPlayer(String playerName) {
        // create instance of player
        Player player = new Player(playerName);

        // Add player in queue
        players.add(player);

        // Add player in snakeAndLadderBoard
        Map<String, Integer> playerPieces = snakeAndLadderBoard.getPlayerPieces();
        playerPieces.put(player.getId(), 0); //Each player has a piece which is initially kept outside the board (i.e., at position 0).
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }


    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        // Iterate until there is no other snake/ladder at the tail of the snake
        // or the end position of the ladder
        Map<Integer, Snake> snakes = snakeAndLadderBoard.getSnakes();
        Map<Integer, Ladder> ladders = snakeAndLadderBoard.getLadders();

        while(snakes.containsKey(newPosition) || ladders.containsKey(newPosition)) {
            // If final position has snake head then move to the tail and update the final position
            if(snakes.containsKey(newPosition)) {
                newPosition = snakes.get(newPosition).getTail();
            }
            // If final position has ladder start position then move to the end position and update the final position
            if(ladders.containsKey(newPosition)) {
                newPosition = ladders.get(newPosition).getEnd();
            }
        }
        return newPosition;
    }

    private void movePlayer(Player player, int diceValue) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        // Based on the dice value, the player moves their piece forward that number of cells.
        int newPosition = oldPosition + diceValue;

        if(!isValidMove(newPosition)) {
            // After the dice roll, if a piece is supposed to move outside position 100, it does not move.
            newPosition = oldPosition;
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);
        System.out.println(player.getPlayerName() + " rolled a " + diceValue + " and moved from " + oldPosition + " " + newPosition);
    }

    private int getTotalValueAfterDiceRolls() {
        return DiceService.roll(noOfDices);
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeAndLadderBoard.getSize();
        // A player wins if it exactly reaches the position = board size and the game ends there for the given player.
        return playerPosition == winningPosition;
    }

    private boolean isGameCompleted() {
        return players.size() <= 1;
    }

    private boolean isValidMove(int position) {
        return  (position > 0 && position <= snakeAndLadderBoard.getSize());
    }

    private int play6() {
        // check for consecutive 6's
        int val = getTotalValueAfterDiceRolls();

        if(val == 6) val += getTotalValueAfterDiceRolls(); // if second consecutive 6
        else return val;

        if(val == 12) return 0; // if third consecutive 6
        return 6+val;
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player player = players.poll();

            // Each player rolls the dice when their turn comes.
            int totalDiceValue = getTotalValueAfterDiceRolls();

            // check for consecutive 6's
            if(totalDiceValue == 6) {
                totalDiceValue = play6();
            }

            if (totalDiceValue == 0) {
                System.out.println("Skipping " + player.getPlayerName() + " move because of three consecutive 6");
                players.offer(player);
                continue; //skip the current player move
            }

            movePlayer(player, totalDiceValue);

            if(hasPlayerWon(player)) {
                System.out.println(player.getPlayerName() + " wins the game");
            } else {
                players.offer(player);
            }
        }
    }
}
