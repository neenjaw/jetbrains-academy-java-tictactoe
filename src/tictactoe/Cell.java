package tictactoe;

public enum Cell {
    BLANK(true, " "),
    O(false, "O"),
    X(false, "X");

    boolean blank;
    String display;

    Cell(boolean blank, String display) {
        this.blank = blank;
        this.display = display;
    }

    public static Cell fromChar(char c) {
        if (c == 'X') {
            return Cell.X;
        } else if (c == 'O') {
            return Cell.O;
        } else {
            return Cell.BLANK;
        }
    }

    public boolean isBlank() {
        return this.blank;
    }

    @Override
    public String toString() {
        return this.display;
    }
}