package tictactoe;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        Game game = new Game();
        scanner = new Scanner(System.in);
//
//        System.out.print("Enter cells: ");
//        String moves = scanner.next();
//        for (int row = 0; row < game.getBoardRows(); row++) {
//            for (int column = 0; column < game.getBoardColumns(); column++) {
//                int offset = row * game.getBoardRows() + column;
//                char move = moves.charAt(offset);
//                game.setSquare(Cell.fromChar(move), row, column);
//            }
//        }

        boolean gameInProgress = true;
        while (gameInProgress) {
            System.out.println(game);
            while (!getUserMove(game));

            GameState turnResult = game.gameState();
            if (turnResult == GameState.NOT_FINISHED) {
                continue;
            }

            gameInProgress = false;
            System.out.println(game);
            System.out.println(turnResult);
        }



//        System.out.println(game);
    }

    private static boolean getUserMove(Game game) {
        System.out.print("Enter the coordinates: ");

        int x;
        int y;

        if (scanner.hasNextInt()) {
            x = scanner.nextInt();
        } else {
            scanner.next();
            System.out.println("You should enter numbers!");
            return false;
        }

        if (scanner.hasNextInt()) {
            y = scanner.nextInt();
        } else {
            scanner.nextLine();
            System.out.println("You should enter numbers!");
            return false;
        }

        if (!game.isXCoordinateWithinBounds(x)) {
            System.out.println("Coordinates should be from 1 to " + game.getBoardColumns());
            return false;
        } else if (!game.isYCoordinateWithinBounds(y)) {
            System.out.println("Coordinates should be from 1 to " + game.getBoardRows());
            return false;
        }

        if (!game.move(x, y)) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }
}
