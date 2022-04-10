package model;

import enums.ColorType;
import enums.PieceType;

public class Queen extends Piece{
    public Queen(ColorType colorType) {
        super(PieceType.QUEEN, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        // any number of steps either horizontally or vertically

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);

        if(startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        int dx = endCoordinates[0]-startCoordinates[0], dy = endCoordinates[1]-startCoordinates[1];

        // if horizontal or vertical
        if ((startCoordinates[0] == endCoordinates[0]) || (startCoordinates[1] == endCoordinates[1])) {
            // if there is piece between start and end position then return false as Rook cannot leap over any other piece
            if(startCoordinates[0] == endCoordinates[0]) {
                // only col changes
                int startY = endCoordinates[1] <= startCoordinates[1] ? endCoordinates[1]+1 : startCoordinates[1]+1;
                int endY = endCoordinates[1] <= startCoordinates[1] ? startCoordinates[1] : endCoordinates[1];
                while(startY < endY) {
                    if(board.getCellAtLocation(startCoordinates[0], startY).getPiece() != null) return false;
                    startY++;
                }
            } else if (startCoordinates[1] == endCoordinates[1]) {
                // only row changes
                int startX = endCoordinates[0] <= startCoordinates[0] ? endCoordinates[0]+1 : startCoordinates[0]+1;
                int endX = endCoordinates[0] <= startCoordinates[0] ? startCoordinates[0] : endCoordinates[0];
                while(startX < endX) {
                    if(board.getCellAtLocation(startX, startCoordinates[1]).getPiece() != null) return false;
                    startX++;
                }
            }
        }

        // else if diagonal
        else if (Math.abs(dx) == Math.abs(dy)) {
            // diagonal move
            /*
                for 1 step
                    up_left --> r--, c--
                    up_right --> r--, c++
                    down_left --> r++, c--
                    down_right --> r++, c++
            */

            // if there is piece between start and end position then return false as Queen cannot leap over any other piece
            for (int i=1, j=1; i<dx && j<dy; i++, j++) {
                int x = startCoordinates[0] + (dx < 0 ? -i : i);
                int y = startCoordinates[1] + (dy < 0 ? -j : j);

                if (board.getCellAtLocation(x, y).getPiece() != null) return false;
            }
        }
        else {
            return false;
        }

        return isValidColorMove(start.getPiece(), end.getPiece());
    }
}
