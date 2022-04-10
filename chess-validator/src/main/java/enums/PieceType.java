package enums;

public enum PieceType {
    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private String pieceType;

    PieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public String getType() {
        return this.pieceType;
    }

    public PieceType getPieceType(String p) {
        for (PieceType pt: PieceType.values()) {
            if(pt.getType().equals(p)) return pt;
        }

        return null;
    }
}
