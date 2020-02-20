package tictactoe;

public enum GameState {
    DRAW("Draw"),
    IMPOSSIBLE("Impossible"),
    NOT_FINISHED("Game not finished"),
    O_WINS("O wins"),
    X_WINS("X wins");

    String message;

    GameState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
