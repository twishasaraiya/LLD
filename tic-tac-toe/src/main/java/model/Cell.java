package model;

public class Cell {
    Integer xPosition;
    Integer yPosition;
    PieceType pieceType;

    public Cell(Integer xPosition, Integer yPosition, PieceType pieceType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.pieceType = pieceType;
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public PieceType getCellType() {
        return pieceType;
    }
}
