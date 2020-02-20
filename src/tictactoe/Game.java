package tictactoe;

public class Game {
    private GameBoard board;
    private int xCount;
    private int oCount;
    private Cell nextMove;

    public Game() {
        this.board = new GameBoard();
        this.xCount = 0;
        this.oCount = 0;
        this.nextMove = Cell.X;
    }

    public int getBoardRows() {
        return this.board.getRows();
    }

    public int getBoardColumns() {
        return this.board.getColumns();
    }

    public boolean move(int x, int y) {
        if (!setSquareFromCoordinate(this.nextMove, x, y)) {
            return false;
        }

        switch (this.nextMove) {
            case X:
                this.nextMove = Cell.O;
                break;
            case O:
                this.nextMove = Cell.X;
                break;
        }

        return true;
    }

    public boolean setSquare(Cell move, int row, int column) {
        if (!this.board.setSquare(move, row, column)) {
             return false;
        }

        switch (move) {
            case X:
                this.xCount++;
                break;
            case O:
                this.oCount++;
                break;
        }

        return true;
    }

    public boolean setSquareFromCoordinate(Cell move, int x, int y) {
        return setSquare(move, getBoardRows() - y, x - 1);
    }

    public GameState gameState() {
        if (Math.abs(this.xCount - this.oCount) >= 2) {
            return GameState.IMPOSSIBLE;
        }

        // Check the rows/columns/diagonals for a win
        Cell[][] sequences = getAllSequences();
        boolean xWin = checkSequencesForWin(Cell.X, sequences);
        boolean oWin = checkSequencesForWin(Cell.O, sequences);

        if (xWin && oWin) {
            return GameState.IMPOSSIBLE;
        } else if (xWin) {
            return GameState.X_WINS;
        } else if (oWin) {
            return GameState.O_WINS;
        }

        if (this.xCount + this.oCount == 9) {
            return GameState.DRAW;
        }

        return GameState.NOT_FINISHED;
    }

    private Cell[][] getAllSequences() {
        return new Cell[][] {
          getRow(0), getRow(1), getRow(2),
          getColumn(0), getColumn(1), getColumn(2),
          getLeftDiagonal(), getRightDiagonal()
        };
    }

    private Cell[] getRow(int row) {
        return new Cell[]{
            this.board.getSquare(row, 0),
            this.board.getSquare(row, 1),
            this.board.getSquare(row, 2)
        };
    }

    private Cell[] getColumn(int column) {
        return new Cell[]{
            this.board.getSquare(0, column),
            this.board.getSquare(1, column),
            this.board.getSquare(2, column)
        };
    }

    private Cell[] getRightDiagonal() {
        return new Cell[]{
                this.board.getSquare(0,0),
                this.board.getSquare(1,1),
                this.board.getSquare(2,2)
        };
    }

    private Cell[] getLeftDiagonal() {
        return new Cell[]{
                this.board.getSquare(2,0),
                this.board.getSquare(1,1),
                this.board.getSquare(0,2)
        };
    }

    private boolean checkSequencesForWin(Cell move, Cell[][] sequences) {
        boolean foundWin = false;

        for (int i = 0; !foundWin && i < sequences.length; i++) {
            if (win(move, sequences[i])) {
                foundWin = true;
            }
        }

        return foundWin;
    }

    private boolean win(Cell move, Cell[] sequence) {
        if (sequence[0] == move && allCellsSame(sequence)) {
            return true;
        }

        return false;
    }

    private boolean allCellsSame(Cell[] sequence) {
        Cell first = sequence[0];

        boolean matching = true;
        for (int i = 1; matching && i < sequence.length; i++) {
            if (sequence[i] != first) {
                matching = false;
            }
        }

        return matching;
    }

    @Override
    public String toString() {
        return this.board.toString(); // + "\n" + this.gameState();
    }

    public boolean isXCoordinateWithinBounds(int x) {
        return x >= 1 && x <= getBoardColumns();
    }

    public boolean isYCoordinateWithinBounds(int y) {
        return y >= 1 && y <= getBoardRows();
    }
}
