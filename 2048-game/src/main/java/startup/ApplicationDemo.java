package startup;

import model.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int direction;
        Game game = new Game();
        game.startGame();
        while(!game.isGameOver()){
            System.out.print("Enter Move:: ");
            direction = Integer.parseInt(br.readLine());
            game.executeMove(direction);
        }
    }
}
