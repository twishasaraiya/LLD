package service;

import enums.ColorType;
import model.Board;
import model.Cell;
import model.Piece;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardService {
    private Board board;
    private Player[] players;
    private int currIdx;
    private static final int DEFAULT_BOARD_SIZE = 8;

    public ChessBoardService() {
        this(DEFAULT_BOARD_SIZE);
    }

    public ChessBoardService(int size) {
        this.board = new Board(size);
        this.players = new Player[2];
        this.currIdx = 0;

        initializePlayer();
    }

    public void initializePlayer() {
        players[0] = new Player("p1", "player1", ColorType.WHITE);
        players[1] = new Player("p2", "player2", ColorType.BLACK);
    }

    public boolean play(String startPosition, String endPosition) {
        Player player = players[currIdx];

        Cell start = board.getCellAtLocation(startPosition);
        Cell end = board.getCellAtLocation(endPosition);

        if (start == null || end == null) {
            System.out.println("Invalid move");
            return false;
        }

        // Piece should not be null at start position
        if(start.getPiece() == null) {
            System.out.println("Piece does not exist at " + startPosition);
            System.out.println("Invalid move");
            return false;
        }

        // if player having white piece is moving the black piece or vice versa
        if(player.getColor() != start.getPiece().getColorType()) {
            System.out.println("It is " + player.getColor() + " turn");
            System.out.println("Invalid move");
            return false;
        }

        if(start.getPiece().isValidMove(board, start, end)) {
            // change turn of player
            currIdx = 1-currIdx;

            // end position will have piece which is equal to start position
            end.setPiece(start.getPiece());

            // start piece becomes null
            start.setPiece(null);

            board.printBoard();
        } else {
            System.out.println("Invalid move");
        }

        return false;
    }
}
