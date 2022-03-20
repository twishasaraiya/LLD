package startup;

import services.SnakeAndLadderGameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System
                .in));

        // Read board size
        System.out.println("Enter board size");
        int boardSize = Integer.parseInt(br.readLine());
        SnakeAndLadderGameService snakeAndLadderGameService = new SnakeAndLadderGameService(boardSize);

        // Read Snakes position
        System.out.println("Enter number of snakes");
        int numSnakes = Integer.parseInt(br.readLine());
        System.out.println("Enter " + numSnakes + " head and tail position of snake");
        for(int i=1; i<=numSnakes; i++) {
            String[] cmd = br.readLine().split(" ");
            snakeAndLadderGameService.addSnakePosition(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]));
        }

        // Read Ladders position
        System.out.println("Enter number of ladders");
        int numLadders = Integer.parseInt(br.readLine());
        System.out.println("Enter " + numLadders + " start and end position of ladder");
        for(int i=1; i<=numLadders; i++) {
            String[] cmd = br.readLine().split(" ");
            snakeAndLadderGameService.addLadderPosition(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]));
        }

        // Read Players who are playing the game
        System.out.println("Enter number of players");
        int numPlayers = Integer.parseInt(br.readLine());
        System.out.println("Enter " + numPlayers + " player name");
        for(int i=1; i<=numPlayers; i++) {
            snakeAndLadderGameService.addPlayer(br.readLine());
        }

        System.out.println("Enter number of dice");
        int numDice = Integer.parseInt(br.readLine());
        snakeAndLadderGameService.setNoOfDices(numDice);

        snakeAndLadderGameService.startGame();
    }
}
