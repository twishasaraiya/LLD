package model;

import enums.ColorType;
import enums.PieceType;

public class Rook extends Piece {
    public Rook(ColorType colorType) {
        super(PieceType.ROOK, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        // any number of steps either horizontally or vertically

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);

        if(startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        // if neither horizontal nor vertical
        if ((startCoordinates[0] != endCoordinates[0]) && (startCoordinates[1] != endCoordinates[1]))
            return false;

        // if there is piece between start and end position then return false as Rook cannot leap over any other piece
        if(startCoordinates[0] == endCoordinates[0]) { // horizontal move
            // only col changes
            int startY = endCoordinates[1] <= startCoordinates[1] ? endCoordinates[1]+1 : startCoordinates[1]+1;
            int endY = endCoordinates[1] <= startCoordinates[1] ? startCoordinates[1] : endCoordinates[1];
            while(startY < endY) {
                if(board.getCellAtLocation(startCoordinates[0], startY).getPiece() != null) return false;
                startY++;
            }
        } else if (startCoordinates[1] == endCoordinates[1]) { // vertical move
            // only row changes
            int startX = endCoordinates[0] <= startCoordinates[0] ? endCoordinates[0]+1 : startCoordinates[0]+1;
            int endX = endCoordinates[0] <= startCoordinates[0] ? startCoordinates[0] : endCoordinates[0];
            while(startX < endX) {
                if(board.getCellAtLocation(startX, startCoordinates[1]).getPiece() != null) return false;
                startX++;
            }
        }

        return isValidColorMove(start.getPiece(), end.getPiece());
    }
}
