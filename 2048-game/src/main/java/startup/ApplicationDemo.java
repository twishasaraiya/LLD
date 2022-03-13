package startup;

import model.Game;

import java.io.IOException;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.startGame();
    }
}
