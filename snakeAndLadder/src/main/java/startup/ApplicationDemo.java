package startup;

import model.ComponentType;
import service.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        GameService gameService = GameService.newGame();
        int numSnakes = Integer.parseInt(br.readLine());
        for (int i = 0; i < numSnakes; i++) {
            String pair[] = br.readLine().split(" ");
            gameService.addGameComponent(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]),  ComponentType.SNAKE);
        }

        int numLadders = Integer.parseInt(br.readLine());
        for (int i = 0; i < numLadders; i++) {
            String pair[] = br.readLine().split(" ");
            gameService.addGameComponent(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]), ComponentType.LADDER);
        }

        int numPlayers = Integer.parseInt(br.readLine());
        for (int i = 0; i < numPlayers; i++) {
            String name = br.readLine();
            gameService.addPlayer(name);
        }

        gameService.startGame();
    }
}
