package model;

import enums.ColorType;
import enums.PieceType;

public class King extends Piece {
    public King(ColorType colorType) {
        super(PieceType.KING, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        // move one step in any direction (horizontally, vertically, or diagonally).
        int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);

        if(startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        boolean isValid = false;

        for (int i = 0; i < dx.length; i++) {
            if ((startCoordinates[0] + dx[i] == endCoordinates[0]) && (startCoordinates[1] + dy[i] == endCoordinates[1])) {
                isValid = true;
                break;
            }
        }

        return isValid && isValidColorMove(start.getPiece(), end.getPiece());
    }
}