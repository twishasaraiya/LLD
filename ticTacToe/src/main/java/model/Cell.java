package model;

public class Cell {
    private final int row;
    private final int col;
    private char piece;

    public Cell(int row, int col, char piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public char getPiece() {
        return piece;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }
}
