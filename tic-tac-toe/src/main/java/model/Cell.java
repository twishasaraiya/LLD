package model;

public class Cell {
    private Integer xPosition;
    private Integer yPosition;
    private PieceType pieceType;

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

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
