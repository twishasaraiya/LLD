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

    public void startGame() {
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
        for(int i=0; i<BOARD_SIZE; i++) {
            // move zero's to end of the row
            int idx = 0;
            for(int j=0; j<BOARD_SIZE; j++){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(i, idx, board.getValueAt(i, j));
                    idx++;
                }
            }
            while(idx < BOARD_SIZE){
                board.updateBoard(i, idx, 0);
                idx++;
            }
            // then merge neighbour tiles if possible
            for(int j=0; j<BOARD_SIZE-1; j++){
                if(board.getValueAt(i, j) == board.getValueAt(i, j+1)){
                    board.updateBoard(i, j, board.getValueAt(i, j) + board.getValueAt(i, j+1));
                    board.updateBoard(i, j+1, 0);
                }
            }
            // again move zero's to end of the row
            idx = 0;
            for(int j=0; j<BOARD_SIZE; j++){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(i, idx, board.getValueAt(i, j));
                    idx++;
                }
            }
            while(idx < BOARD_SIZE){
                board.updateBoard(i, idx, 0);
                idx++;
            }
        }
    }

    private void moveAllTilesRight(){
        for(int i=0; i<BOARD_SIZE; i++){
            // move zero's to start of the row
            int idx = BOARD_SIZE-1;
            for(int j=BOARD_SIZE-1; j>=0; j--){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(i, idx, board.getValueAt(i, j));
                    idx--;
                }
            }
            while(idx >= 0){
                board.updateBoard(i, idx, 0);
                idx--;
            }
            // then merge neighbour tiles if possible
            for(int j=BOARD_SIZE-1; j>0; j--){
                if(board.getValueAt(i, j) == board.getValueAt(i, j-1)){
                    board.updateBoard(i, j, board.getValueAt(i, j) + board.getValueAt(i, j-1));
                    board.updateBoard(i, j-1, 0);
                }
            }
            // again move zero's to start of the row
            idx = BOARD_SIZE-1;
            for(int j=BOARD_SIZE-1; j>=0; j--){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(i, idx, board.getValueAt(i, j));
                    idx--;
                }
            }
            while(idx >= 0){
                board.updateBoard(i, idx, 0);
                idx--;
            }
        }
    }
    private void moveAllTilesTop(){
        for(int j=0; j<BOARD_SIZE; j++){
            // move zero's to top of the column
            int idx = 0;
            for(int i=0; i<BOARD_SIZE; i++){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(idx, j, board.getValueAt(i, j));
                    idx++;
                }
            }
            while(idx < BOARD_SIZE){
                board.updateBoard(idx, j, 0);
                idx++;
            }
            // then merge neighbour tiles if possible
            for(int i=0; i<BOARD_SIZE-1; i++){
                if(board.getValueAt(i, j) == board.getValueAt(i+1, j)){
                    board.updateBoard(i, j, board.getValueAt(i, j) + board.getValueAt(i+1, j));
                    board.updateBoard(i+1, j, 0);
                }
            }
            // again move zero's to top of the row
            idx = 0;
            for(int i=0; i<BOARD_SIZE; i++){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(idx, j, board.getValueAt(i, j));
                    idx++;
                }
            }
            while(idx < BOARD_SIZE){
                board.updateBoard(idx, j, 0);
                idx++;
            }
        }
    }
    private void moveAllTilesBottom(){
        for(int j=0; j<BOARD_SIZE; j++){
            // move zero's to bottom of the column
            int idx = BOARD_SIZE-1;
            for(int i=BOARD_SIZE-1; i>=0; i--){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(idx, j, board.getValueAt(i, j));
                    idx--;
                }
            }
            while(idx >= 0){
                board.updateBoard(idx, j, 0);
                idx--;
            }
            // then merge neighbour tiles if possible
            for(int i=BOARD_SIZE-1; i>0; i--){
                if(board.getValueAt(i, j) == board.getValueAt(i-1, j)){
                    board.updateBoard(i, j, board.getValueAt(i, j) + board.getValueAt(i-1, j));
                    board.updateBoard(i-1, j, 0);
                }
            }
            // again move zero's to bottom of the column
            idx = BOARD_SIZE-1;
            for(int i=BOARD_SIZE-1; i>=0; i--){
                if(board.getValueAt(i, j) != 0){
                    board.updateBoard(idx, j, board.getValueAt(i, j));
                    idx--;
                }
            }
            while(idx >= 0){
                board.updateBoard(idx, j, 0);
                idx--;
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
