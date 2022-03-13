package model;

public class Board {
    private final int gridSize;
    private Cell[][] grid;

    public Board(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new Cell[gridSize][gridSize];
        initializeBoard();
        printBoard();
    }

    private void initializeBoard() {
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

    public Cell[][] getGrid() {
        return grid;
    }
}
