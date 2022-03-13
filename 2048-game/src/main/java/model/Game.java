package model;

import enums.MoveDirection;

import java.io.IOException;
import java.util.Random;

public class Game {

    private static final int BOARD_SIZE = 4; // can be taken as input from user
    private static final int DEFAULT_VALUE = 2;
    private static final int WINNING_NUMBER = 2048;
    private final Board board;
    private Boolean gameOver;
    private final Random random;

    public Game(){
        this.board = new Board(BOARD_SIZE);
        this.gameOver = Boolean.FALSE;
        random = new Random();
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void startGame() throws IOException {
        System.out.println("Game started");
        fillRandomTile(); fillRandomTile();
        displayBoard();
    }

    public void executeMove(int direction){
        MoveDirection moveDirection = MoveDirection.getDirection(direction);
        if(moveDirection == null){
            System.out.println("Invalid Direction");
            return;
        }
        switch (moveDirection){
            case LEFT:
                moveAllTilesLeft();
                break;
            case RIGHT:
                moveAllTilesRight();
                break;
            case TOP:
                moveAllTilesTop();
                break;
            case BOTTOM:
                moveAllTilesBottom();
                break;
        }
        fillRandomTile();
        displayBoard();
        if(hasGameWon()){
            System.out.println("Congratulations");
            gameOver = Boolean.TRUE;
            return;
        }
        if(hasGameLost()){
            System.out.println("Game over");
            gameOver = Boolean.TRUE;
        }
    }

    private void moveAllTilesLeft(){
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=1; j<BOARD_SIZE; j++){
                if(board.getValueAt(i, j) == 0) continue;
                int k = j-1;
                while(k >= 0 && board.getValueAt(i, k) == 0){
                    k--;
                }
                // all 0 left of tile
                if(k < 0){
                    board.updateBoard(i, k+1, board.getValueAt(i, j));
                    board.updateBoard(i, j, 0);
                }
                else{
                    int currTileValue = board.getValueAt(i, j);
                    int prevTileValue = board.getValueAt(i, k);
                    // left non-zero tile have same value as current tile
                    if(currTileValue == prevTileValue){
                        board.updateBoard(i, k, currTileValue+prevTileValue);
                        board.updateBoard(i, j, 0);
                    }
                    // left non-zero tile have different value as current tile
                    else{
                        // current tile can't move left
                        board.updateBoard(i, k+1, currTileValue);
                        if(k != j-1) board.updateBoard(i, j, 0);
                    }
                }

            }
        }
    }

    private void moveAllTilesRight(){
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=BOARD_SIZE-2; j>=0; j--){
                if(board.getValueAt(i, j) == 0) continue;
                int k = j+1;
                while(k < BOARD_SIZE && board.getValueAt(i, k) == 0){
                    k++;
                }
                // all 0 right of current tile
                if(k == BOARD_SIZE){
                    board.updateBoard(i, k-1, board.getValueAt(i, j));
                    board.updateBoard(i, j, 0);
                }
                else{
                    int currTileValue = board.getValueAt(i, j);
                    int nextTileValue = board.getValueAt(i, k);
                    if(currTileValue == nextTileValue){
                        board.updateBoard(i, k, currTileValue+nextTileValue);
                        board.updateBoard(i, j, 0);
                    }
                    else{
                        board.updateBoard(i, k-1, currTileValue);
                        if(k != j+1) board.updateBoard(i, j, 0);
                    }
                }
            }
        }
    }
    private void moveAllTilesTop(){
        for(int j=0; j<BOARD_SIZE; j++){
            for(int i=1; i<BOARD_SIZE; i++){
                if(board.getValueAt(i, j) == 0) continue;
                int k = i-1;
                while(k >= 0 && board.getValueAt(k, j) == 0){
                    k--;
                }
                if(k < 0){
                    board.updateBoard(k+1, j, board.getValueAt(i, j));
                    board.updateBoard(i, j, 0);
                }
                else{
                    int currTileValue = board.getValueAt(i, j);
                    int upTileValue = board.getValueAt(k, j);
                    if(currTileValue == upTileValue){
                        board.updateBoard(k, j, currTileValue+upTileValue);
                        board.updateBoard(i, j, 0);
                    }
                    else{
                        board.updateBoard(k+1, j, currTileValue);
                        if(k != i-1) board.updateBoard(i, j, 0);
                    }
                }
            }
        }
    }
    private void moveAllTilesBottom(){
        for(int j=0; j<BOARD_SIZE; j++){
            for(int i=BOARD_SIZE-2; i>=0; i--){
                if(board.getValueAt(i, j) == 0) continue;
                int k = i+1;
                while(k < BOARD_SIZE && board.getValueAt(k, j) == 0){
                    k++;
                }
                if(k == BOARD_SIZE){
                    board.updateBoard(k-1, j, board.getValueAt(i, j));
                    board.updateBoard(i, j, 0);
                }
                else{
                    int currTileValue = board.getValueAt(i, j);
                    int downTileValue = board.getValueAt(k, j);
                    if(currTileValue == downTileValue){
                        board.updateBoard(k, j, currTileValue+downTileValue);
                        board.updateBoard(i, j, 0);
                    }
                    else{
                        board.updateBoard(k-1, j, currTileValue);
                        if(k != i+1) board.updateBoard(i, j, 0);
                    }
                }
            }
        }
    }

    private void fillRandomTile(){
        boolean cellFound = Boolean.FALSE;
        int x = 0, y = 0;
        while (!cellFound){
            x = random.nextInt(BOARD_SIZE);
            y = random.nextInt(BOARD_SIZE);
            if(board.getValueAt(x, y) == 0){
                cellFound = Boolean.TRUE;
            }
        }
        board.updateBoard(x, y, DEFAULT_VALUE);
    }

    private boolean hasGameWon(){
        return board.getMaxValue() == WINNING_NUMBER;
    }

    private boolean hasGameLost(){
        return board.isBoardFull();
    }

    private void displayBoard(){
        board.displayBoard();
    }

}
