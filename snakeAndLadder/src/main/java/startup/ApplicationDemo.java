package startup;

import model.SnakeAndLadderBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System
                .in));

        // Read board size
        int boardSize = Integer.parseInt(br.readLine());
        SnakeAndLadderBoard snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);

        // Read Snakes position
        int numSnakes = Integer.parseInt(br.readLine());
        for(int i=1; i<=numSnakes; i++) {
            String[] cmd = br.readLine().split(" ");
            snakeAndLadderBoard.addSnakePosition(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]));
        }

        // Read Ladders position
        int numLadders = Integer.parseInt(br.readLine());
        for(int i=1; i<=numLadders; i++) {
            String[] cmd = br.readLine().split(" ");
            snakeAndLadderBoard.addLadderPosition(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]));
        }

        // Read Players who are playing the game
        int numPlayers = Integer.parseInt(br.readLine());
        for(int i=1; i<=numPlayers; i++) {
            String[] cmd = br.readLine().split(" ");
            snakeAndLadderBoard.addPlayer(cmd[0]);
        }

        // Read the choice whether the game is played with 2 dice or not
        System.out.println("Do you want to play with 2 dice");
        System.out.println("Enter  \n1 for No \n2 for Yes");
        boolean isPlayWith2Dice =  Integer.parseInt(br.readLine()) == 2;
        snakeAndLadderBoard.setPlayWith2Dice(isPlayWith2Dice);
        snakeAndLadderBoard.playGame();
    }
}
