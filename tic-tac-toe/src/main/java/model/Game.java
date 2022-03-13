package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Grid grid;
    List<Player> players;
    private static final Integer GRID_SIZE = 3;
    private static final Integer TOTAL_PLAYERS = 2;
    private Queue<Player> playerQueue;
    private Integer emptyCellsCount;


    private int[] rowSum;
    private int[] colSum;
    private int diagSum;
    private int antiDiagSum;

    public Game() {
        initializeGrid();
        this.emptyCellsCount = GRID_SIZE * GRID_SIZE;
        this.players = new ArrayList<>(TOTAL_PLAYERS);  // kachra
        this.playerQueue = new LinkedList<>();
        rowSum = new int[GRID_SIZE];
        colSum = new int[GRID_SIZE];
        diagSum = 0;
        antiDiagSum = 0;
    }

    private void initializeGrid(){
        this.grid = new Grid();
        for (int i = 0; i < GRID_SIZE; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < GRID_SIZE; j++) {
                row.add(new Cell(i,j, PieceType.EMPTY));
            }
            grid.addRow(row);
        }
        grid.printGrid();
    }

    public void addPlayer(String name, String piece){
        if(players.size() >=  TOTAL_PLAYERS){
            System.out.println("Two players already present in the game. Cannot add new Player. Please start a new game");
        }
        Player newPlayer = new Player(name, PieceType.valueOf(piece));
        players.add(newPlayer);
        playerQueue.add(newPlayer);
    }

    public void takeTurn(Integer xPosition, Integer yPosition) throws Exception {
        if(!isValidPosition(xPosition-1, yPosition-1)){
            throw new IllegalArgumentException("Invalid cell position");
        }

        if(isGridFull()){
            System.out.println("Game over!!");
            return;
        }

        if(!grid.getPieceTypeByPosition(xPosition, yPosition).equals(PieceType.EMPTY)){
            System.out.println("Invalid Move");
            return;
        }

        Player player = getCurrentPlayerTurn();
        this.grid.updateCellTypeAtPosition(xPosition, yPosition, player.getPiece());

        decrementEmptyCellCount();
        grid.printGrid();

        if (hasPlayerWonTheGame(xPosition, yPosition, player)){
            System.out.println(player.getName() + " wins!!!");
            System.out.println("exit");
            return;
        }

        updatePlayerTurn();
    }

    private void updatePlayerTurn(){
        playerQueue.add(playerQueue.poll());
    }

    private void decrementEmptyCellCount(){
        this.emptyCellsCount--;
    }

    private Boolean isGridFull(){
        return this.emptyCellsCount == 0;
    }

    private Boolean hasPlayerWonTheGame(int xPosition, int yPosition, Player player){

        int point = player.getPiece().equals(PieceType.X) ? 1 : -1;
        rowSum[xPosition-1] += point;
        colSum[yPosition-1] += point;

        if (xPosition == yPosition){
            diagSum += point;
        }

        if (xPosition == GRID_SIZE - 1 - yPosition){
            antiDiagSum += point;
        }

        if(Math.abs(rowSum[xPosition-1]) == GRID_SIZE || Math.abs(colSum[yPosition-1]) == GRID_SIZE || diagSum == GRID_SIZE || antiDiagSum == GRID_SIZE){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    private Player getCurrentPlayerTurn(){ // this method has to be syncronized since we update the player turn
        return playerQueue.peek();
    }

    private Boolean isValidPosition(Integer x, Integer y){
        if(x >=0 && x<GRID_SIZE && y>=0 && y<GRID_SIZE) return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
