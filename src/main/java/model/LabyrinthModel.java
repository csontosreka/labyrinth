package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Class representing the state of the puzzle.
 */
public class LabyrinthModel implements Levels{

    /**
     * Represents the size of the board.
     */
    public static int BOARD_SIZE = 9;

    /**
     * The array that stores the board.
     */
    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

    /**
     * Loads the board from the Levels interface.
     */
    public LabyrinthModel() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(lvl1Board[i][j]);
            }
        }
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    /**
     * {@return the state of the position on the board}
     *
     * @param i the row of a position
     * @param j the column of a position
     */
    public Square getSquare(int i, int j) {
        return board[i][j].get();
    }

    /**
     * Checks if the position is on the board.
     * @param position the current position.
     * {@return {@code true} if the position is on the board, {@code false} otherwise.}
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new LabyrinthModel();
        System.out.println(model);
    }

}
