package service;

import model.Game;

public class GameService {

    private Game game;

    public GameService() {
        newGame();
    }

    private void newGame(){
        this.game = new Game();
    }

    public void addPlayer(String name, String piece){
        this.game.addPlayer(name, piece);
    }

    public void takeTurn(Integer xPosition, Integer yPosition) throws Exception {
        this.game.takeTurn(xPosition, yPosition);
    }
}
