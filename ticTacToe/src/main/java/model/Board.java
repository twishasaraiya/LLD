package model;

import enums.GameStatus;

public class Board {
    private int gridSize;
    private Cell[][] grid;
    int count;

    public Board(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new Cell[gridSize][gridSize];
        this.count = 0;
        initializeBoard();
    }

    private void initializeBoard() {
        System.out.println("grid size " + gridSize);
        for(int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                grid[i][j] = new Cell(i, j, '-');
            }
        }
    }

    public void printBoard() {
        for(int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                System.out.print(grid[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int row, int col) {
        if(row>=gridSize || row<0 || col>=gridSize || col<0 || grid[row][col].getPiece() != '-') return false;
        return true;
    }


    private boolean hasWonGame(int row, int col, char piece) {
        boolean rowWin = true, colWin = true, lowerDiagWin = true, upperDiagWin = true;
        for(int i=0; i<gridSize; i++) {
            // check for the given row only
            if(grid[row][i].getPiece() != piece) rowWin = false;

            // check for the given col only
            if(grid[i][col].getPiece() != piece) colWin = false;

            // check for the given lower diagonal only
            if(grid[i][i].getPiece() != piece) lowerDiagWin = false;

            // check for the given upper diagonal only
            if(grid[i][gridSize-1-i].getPiece() != piece) upperDiagWin = false;
        }
        return (rowWin || colWin || lowerDiagWin || upperDiagWin);
    }

    public GameStatus playGame(int row, int col, Player player) {
        if(isValidMove(row-1, col-1)) {

            char piece = player.getPiece();
            grid[row-1][col-1].setPiece(piece);
            printBoard();
            count++;

            // Won game
            if(hasWonGame(row-1, col-1, piece)) {
                System.out.println(player.getName() + " won the game");
                return GameStatus.WON;
            }

            // Draw condition
            if(count == gridSize*gridSize) {
                System.out.println("Game over");
                return GameStatus.DRAW;
            }
        } else {
            System.out.println("Invalid Move");
            return GameStatus.INVALID;
        }
        return GameStatus.VALID;
    }

}
