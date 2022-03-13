package service;

import enums.GameStatus;
import model.Board;
import model.Cell;
import model.Player;
import java.util.LinkedList;
import java.util.Queue;

public class GameService {
    private final int gridSize;
    private final Board board;
    private Queue<Player> players;
    private int count; // to keep count of total valid moves

    // only for 2 players
    /*
        private int[] rowArr;
        private int[] colArr;
        private int diag;
        private int antiDiag;
        private int currentPlayerIdx;
    * */

    public GameService(int gridSize) {
        this.gridSize = gridSize;
        this.board = new Board(gridSize);
        this.players = new LinkedList<>();
        this.count = 0;

        // only for 2 players
        /*
            this.rowArr = new int[gridSize];
            this.colArr = new int[gridSize];
            this.diag = 0;
            this.antiDiag = 0;
            this.currentPlayerIdx = 0;
        */
    }

    public void addPlayer(char piece, String name) {
        players.add(new Player(name, piece));
    }

    private boolean isValidMove(int row, int col) {
        // 0-based indexing
        if(row>=gridSize || row<0 || col>=gridSize || col<0 || board.getGrid()[row][col].getPiece() != '-')
            return false;
        return true;
    }


    private boolean hasWonGame(int row, int col, char piece) {
        /*


            //////////////////////  O(1) complexity   ////////////////////////
            ///////////////////// Applicable for only 2 players ///////////////


            int val = currentPlayerIdx == 1 ? 1 : -1;
            rowArr[row] += val;
            colArr[col] += val;
            if(row == col) diag += val;
            if(row + col == gridSize-1) antiDiag += val;

            return (rowArr[row] == Math.abs(gridSize) || colArr[col] == Math.abs(gridSize) ||  diag == Math.abs(gridSize) ||  antiDiag == Math.abs(gridSize));
        */


        /*********************  O(n) complexity   **************************/
        /*
            0,0  0,1  0,2  i=0, j = size-1-i = 3-1-0 = 2
            1,0  1,1  1,2
            2,0, 2,1  2,2


            X X' X (2nd row, 2nd col)
            - - -
        * */
        boolean rowWin = true, colWin = true, diagWin = true, antiDiagWin = true;
        Cell[][] grid = board.getGrid();
        for(int i=0; i<gridSize; i++) {
            // check for the given row only
            if(grid[row][i].getPiece() != piece) rowWin = false;

            // check for the given col only
            if(grid[i][col].getPiece() != piece) colWin = false;

            // check for the given diagonal only
            if(grid[i][i].getPiece() != piece) diagWin = false;

            // check for the given anti diagonal only
            if(grid[i][gridSize-1-i].getPiece() != piece) antiDiagWin = false;
        }
        return (rowWin || colWin || diagWin || antiDiagWin);

    }

    public GameStatus playGame(int row, int col, Player player) {
        if(isValidMove(row-1, col-1)) {
            char piece = player.getPiece();
            board.getGrid()[row-1][col-1].setPiece(piece);
            board.printBoard();
            count++;

            // Won game
            if(hasWonGame(row-1, col-1, piece)) {
                System.out.println(player.getName() + " won the game");
                return GameStatus.WON;
            }

            // Draw condition
            if(count == gridSize*gridSize) {
                System.out.println("Game over");
                return GameStatus.DRAW;
            }
        } else {
            System.out.println("Invalid Move");
            return GameStatus.INVALID;
        }
        return GameStatus.VALID;
    }


    public boolean hasNextTurn(int row, int col) {
        if(players.size() > 1) {
            Player player = players.peek();
            GameStatus status = playGame(row, col, player);
            if(status.equals(GameStatus.valueOf("VALID"))) {
                player = players.poll();
                players.offer(player);
                return true;
                /*
                * // only for 2 players
                * currentPlayerIdx = currentPlayerIdx == 1 ? 0 : 1;
                * */
            }
            else if(status.equals(GameStatus.valueOf("WON"))) {
                players.poll();
                return players.size() > 1;
            } else if (status.equals(GameStatus.valueOf("DRAW"))) {
                players.clear();
                return false;
            } else if (status.equals(GameStatus.valueOf("INVALID"))) return true;
        }

        System.out.println("There should be at least 2 players playing the Tic Tac Toe Game");
        return false;
    }
}
