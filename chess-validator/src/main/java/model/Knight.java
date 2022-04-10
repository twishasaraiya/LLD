package model;

import enums.ColorType;
import enums.PieceType;

import java.util.Arrays;

public class Knight extends Piece{
    public Knight(ColorType colorType) {
        super(PieceType.KNIGHT, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        // 1 step vertically (row) and 2 steps horizontally (col)
        // 1 step up and 2 step right
        // 1 step up and 2 step left
        // 1 step down and 2 step right
        // 1 step down and 2 step left

        //OR//

        // 2 steps vertically (row) and 1 step horizontally (col)
        // 2 step up and 1 step right
        // 2 step up and 1 step left
        // 2 step down and 1 step right
        // 2 step down and 1 step left

//        int[] dx = new int[]{1, 1, -1, 1, 2, 2, -2, -2};
//        int[] dy = new int[]{2, -2, 2, -2, 1, -1, 1, -1};

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);

        if(startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        int dx = Math.abs(endCoordinates[0]-startCoordinates[0]);
        int dy = Math.abs(endCoordinates[1]-startCoordinates[1]);

        if(dx*dy != 2) return false;

        return isValidColorMove(start.getPiece(), end.getPiece());
    }
}
