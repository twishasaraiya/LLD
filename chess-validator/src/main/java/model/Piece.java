package model;

import enums.ColorType;
import enums.PieceType;

public abstract class Piece {
    private PieceType pieceType;
    private ColorType colorType;

    public Piece(PieceType pieceType, ColorType colorType) {
        this.pieceType = pieceType;
        this.colorType = colorType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public ColorType getColorType() {
        return colorType;
    }

    protected boolean isValidColorMove(Piece startPiece, Piece endPiece) {
        // Check if target cell is empty
        if (endPiece == null) return true;

        // If target cell is not empty then it should contain piece of opposite color
        if (startPiece.getColorType() != endPiece.getColorType())
            return true;

        return false;
    }

    public abstract boolean isValidMove(Board board, Cell start, Cell end);

}
