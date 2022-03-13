package model;

public enum PieceType {
    X('X'),
    O('O'),
    EMPTY('-');

    private Character alias;
    PieceType(char o) {
        alias = o;
    }

    public Character getAlias() {
        return alias;
    }
}
