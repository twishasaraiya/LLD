package model;

public class Player {
    String name;
    PieceType piece; // Naming convention problem

    public Player(String name, PieceType pieceType) {
        this.name = name;
        this.piece = pieceType;
    }

    public String getName() {
        return name;
    }

    public PieceType getPiece() {
        return piece;
    }
}
