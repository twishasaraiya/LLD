package model;

import enums.ColorType;
import enums.PieceType;

public class Pawn extends Piece{
    public Pawn(ColorType colorType) {
        super(PieceType.PAWN, colorType);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {

        int[] startCoordinates = board.getIndexByPosition(start);
        int [] endCoordinates = board.getIndexByPosition(end);
        Piece destPiece = end.getPiece();

        if (startCoordinates[0] == -1 || startCoordinates[1] == -1 || endCoordinates[0] == -1 || endCoordinates[1] == -1)
            return false;

        if (start.getPiece().getColorType() == ColorType.WHITE) {
            return isValidWhitePawnMove(startCoordinates, endCoordinates, destPiece);
        }

        return isValidBlackPawnMove(startCoordinates, endCoordinates, destPiece);
    }

    private boolean isValidWhitePawnMove(int[] startCoordinates, int[] endCoordinates, Piece destPiece) {
        /*
            For white pawn
                - 1st move --> r = r-2 and no piece at end position (default pawn position is 7th row = 6th index)
                - from 2nd move --> r = r-1 and no piece at end position
                - for diagonal move --> if (r-1, c-1) OR (r-1, c+1) has black piece then valid move

         */

        //for vertical move dest cell should be empty
        if (destPiece == null) {
            // if first move, then it can move either 1 or 2 steps
            if (startCoordinates[0] == 6 && ((startCoordinates[0]-2 == endCoordinates[0]) || (startCoordinates[0]-1 == endCoordinates[0])))
                return true;

            // from second move, it can move 1 step
            if (startCoordinates[0] != 6 && (startCoordinates[0]-1 == endCoordinates[0]))
                return true;
        }

        // for diagonal move
        // black piece should exist at dest piece
        else if (destPiece.getColorType() == ColorType.BLACK) {

            /*
                   -1, -1    -1, 1
                       ^       ^
                       |       |
                        \     /
                           WP
            */

            int dx = endCoordinates[0] - startCoordinates[0];
            int dy = endCoordinates[1] - startCoordinates[1];

            if (dx == -1 && (dy == -1 || dy == 1))
                return true;
        }

        return false;
    }

    private boolean isValidBlackPawnMove(int[] startCoordinates, int[] endCoordinates, Piece destPiece) {
         /*
            For black pawn
                - 1st move --> r = r+2 and no piece at end position (default pawn position is 2nd row = 1st index)
                - from 2nd move --> r = r+1 and no piece at end position
                - for diagonal move --> if (r+1, c-1) OR (r+1, c+1) has white piece then valid move
        */

        //for vertical move dest cell should be empty
        if (destPiece == null) {
            // if first move, then it can move either 1 or 2 steps
            if (startCoordinates[0] == 1 && ((startCoordinates[0]+2 == endCoordinates[0]) || (startCoordinates[0]+1 == endCoordinates[0])))
                return true;

            // from second move, it can move 1 step
            if (startCoordinates[0] != 1 && (startCoordinates[0]+1 == endCoordinates[0]))
                return true;
        }

        // for diagonal move
        // white piece should exist at dest piece
        else if (destPiece.getColorType() == ColorType.WHITE) {

            /*
                           BP
                        /      \
                    1, -1    1, 1
            */

            int dx = endCoordinates[0] - startCoordinates[0];
            int dy = endCoordinates[1] - startCoordinates[1];

            if (dx == 1 && (dy == -1 || dy == 1))
                return true;
        }

        return false;
    }
}
