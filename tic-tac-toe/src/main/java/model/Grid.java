package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<List<Cell>> grid;

    public Grid() {
        this.grid = new ArrayList<>();
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void addRow(List<Cell> cellList){
        this.grid.add(cellList);
    }

    public void printGrid(){
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                System.out.print(grid.get(i).get(j).getPieceType().getAlias() + " ");
            }
            System.out.println();
        }
    }

    public void updateCellTypeAtPosition(Integer x, Integer y, PieceType pieceType) {
        Cell cell = this.grid.get(x-1).get(y-1);
        cell.setPieceType(pieceType);
    }

    public PieceType getPieceTypeByPosition(Integer x, Integer y){
        return grid.get(x-1).get(y-1).getPieceType();
    }
}
