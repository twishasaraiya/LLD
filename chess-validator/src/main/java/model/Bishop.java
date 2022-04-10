package model;

import enums.ColorType;
import enums.PieceType;

public class Bishop extends Piece{
    public Bishop(ColorType colorType) {
        super(PieceType.BISHOP, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        // diagonal move
        /*
            for 1 step
                up_left --> r--, c--
                up_right --> r--, c++
                down_left --> r++, c--
                down_right --> r++, c++
        */

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);

        if(startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        int dx = endCoordinates[0]-startCoordinates[0], dy = endCoordinates[1]-startCoordinates[1];

        // if not diagonal
        if (Math.abs(dx) != Math.abs(dy)) return false;

        // if there is piece between start and end position then return false as bishop cannot leap over any other piece
        for (int i=1, j=1; i<dx && j<dy; i++, j++) {
            int x = startCoordinates[0] + (dx < 0 ? -i : i);
            int y = startCoordinates[1] + (dy < 0 ? -j : j);

            if (board.getCellAtLocation(x, y).getPiece() != null) return false;
        }

        return isValidColorMove(start.getPiece(), end.getPiece());
    }
}
