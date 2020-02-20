package tictactoe;

import java.util.Arrays;

public class GameBoard {
    private Cell[][] squares;

    public GameBoard() {
        this.squares = new Cell[][]{
                {Cell.BLANK, Cell.BLANK, Cell.BLANK},
                {Cell.BLANK, Cell.BLANK, Cell.BLANK},
                {Cell.BLANK, Cell.BLANK, Cell.BLANK},
        };
    }

    public Cell getSquare(int row, int column) {
        return this.squares[row][column];
    }

    public boolean setSquare(Cell move, int row, int column) {
        if (row < 0
                || row >= this.squares.length
                || column < 0
                || column >= this.squares[0].length) {
            return false;
        }

        if (this.squares[row][column] != Cell.BLANK) {
            return false;
        }

        this.squares[row][column] = move;

        return true;
    }

    public int getRows() {
        return this.squares.length;
    }

    public int getColumns() {
        return this.squares[0].length;
    }


    @Override
    public String toString() {
        String top = "---------";
        String left = "| ";
        String right = " |";
        String[] rows = new String[3];

        int i = 0;
        for (Cell[] row : this.squares) {
            String[] rowDisplay = Arrays.stream(row).map(Object::toString).toArray(String[]::new);
            rows[i] = left + String.join(" ", rowDisplay) + right;
            i++;
        }

        return top + "\n" + String.join("\n", rows) + "\n" + top;
    }
}
