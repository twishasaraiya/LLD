package model;

import enums.ColorType;

public class Board {
    private int size;
    private Cell[][] cells;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        initializeBoard();
        printBoard();
    }

    private void initializeBoard() {

        // ROW 1
        cells[0][0] = new Cell(8, 'a', new Rook(ColorType.BLACK));
        cells[0][1] = new Cell(8, 'b', new Knight(ColorType.BLACK));
        cells[0][2] = new Cell(8, 'c', new Bishop(ColorType.BLACK));
        cells[0][3] = new Cell(8, 'd', new Queen(ColorType.BLACK));
        cells[0][4] = new Cell(8, 'e', new King(ColorType.BLACK));
        cells[0][5] = new Cell(8, 'f', new Bishop(ColorType.BLACK));
        cells[0][6] = new Cell(8, 'g', new Knight(ColorType.BLACK));
        cells[0][7] = new Cell(8, 'h', new Rook(ColorType.BLACK));

        // ROW 2
        for(int c=0; c<size; c++) {
            cells[1][c] = new Cell(
                    7,
                    (char)('a'+c),
                    new Pawn(ColorType.BLACK)
            );
        }

        // ROW 3 - ROW 6
        for (int r = 2; r < 6; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c] = new Cell(
                        (size-r),
                        (char)('a'+c),
                        null
                );
            }
        }

        // ROW 7
        for(int c=0; c<size; c++) {
            cells[6][c] = new Cell(
                    2,
                    (char)('a'+c),
                    new Pawn(ColorType.WHITE)
            );
        }
        // ROW 8
        cells[7][0] = new Cell(1, 'a', new Rook(ColorType.WHITE));
        cells[7][1] = new Cell(1, 'b', new Knight(ColorType.WHITE));
        cells[7][2] = new Cell(1, 'c', new Bishop(ColorType.WHITE));
        cells[7][3] = new Cell(1, 'd', new Queen(ColorType.WHITE));
        cells[7][4] = new Cell(1, 'e', new King(ColorType.WHITE));
        cells[7][5] = new Cell(1, 'f', new Bishop(ColorType.WHITE));
        cells[7][6] = new Cell(1, 'g', new Knight(ColorType.WHITE));
        cells[7][7] = new Cell(1, 'h', new Rook(ColorType.WHITE));
    }

    public void printBoard() {
        for (int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                Piece piece = cells[i][j].getPiece();
                if (piece == null) {
                    System.out.print("__ ");
                } else {
                    System.out.print(piece.getColorType().getType() + "" + piece.getPieceType().getType() + " ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return cells;
    }

    public Cell getCellAtLocation(String position) {
        int x = size - (position.charAt(1)-'0');
        int y = position.charAt(0)-'a';

        return getCellAtLocation(x, y);
    }

    public Cell getCellAtLocation(Cell cell) {
        int x = size - cell.getRow();
        int y = cell.getCol()-'a';

        return getCellAtLocation(x, y);
    }

    public Cell getCellAtLocation(int x, int y) {
        if(x>=0 && x<size && y>=0 && y<size)
            return cells[x][y];

        System.out.println("Index out of bound");
        return null;
    }

    public int[] getIndexByPosition(String position) {
        int x = size - (position.charAt(1)-'0');
        int y = position.charAt(0)-'a';

        if(x<0 || x>size || y<0 || y>size) {
            System.out.println("Index out of bound");
            return new int[]{-1, -1};
        }

        return new int[]{x, y};
    }

    public int[] getIndexByPosition(Cell cell) {
        int x = size - cell.getRow();
        int y = cell.getCol()-'a';

        if(x<0 || x>size || y<0 || y>size) {
            System.out.println("Index out of bound");
            return new int[]{-1, -1};
        }

        return new int[]{x, y};
    }

}
