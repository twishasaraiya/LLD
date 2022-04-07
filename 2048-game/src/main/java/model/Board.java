package model;

public class Board {
    private static final int[][] dirs = {{0,-1}, {0, 1}, {-1, 0}, {1,0}};
    private final int boardSize;
    private final Tile[][] board;
    private int maxValue;

    public Board(int boardSize){
        this.boardSize = boardSize;
        this.board = new Tile[boardSize][boardSize];
        this.maxValue = 0;
        this.initializeBoard();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void initializeBoard(){
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                board[i][j] = new Tile(i, j);
            }
        }
    }

    public void displayBoard(){
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                System.out.print(board[i][j].value + "  ");
            }
            System.out.println();
        }
    }

    public void updateBoard(int x, int y, int newValue){
        board[x][y].setValue(newValue);
        maxValue = Math.max(maxValue, newValue);
    }

    public int getValueAt(int x, int y){
        return board[x][y].value;
    }


    public boolean isBoardFull(){
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if(board[i][j].value == 0 || sameValueNeighbourExist(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean inBoard(int x, int y){
        return ((x >=0 && x < boardSize) && (y >=0 && y < boardSize));
    }

    private boolean sameValueNeighbourExist(int x, int y){
        int xx, yy;
        for(int[] dir: dirs){
            xx = x + dir[0];
            yy = y + dir[1];
            if(inBoard(xx, yy) && board[xx][yy].value == board[x][y].value){
                return true;
            }
        }
        return false;
    }
}
