package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Class representing the state of the puzzle.
 */
public class LabyrinthModel implements Levels {

    /**
     * Represents the size of the board.
     */
    public static int BOARD_SIZE = 9;

    /**
     * Represents the start row.
     */
    public int startRow = 7;
    /**
     * Represents the start column.
     */
    public int startCol = 3;

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
     *
     * @param position the current position.
     *                 {@return {@code true} if the position is on the board, {@code false} otherwise.}
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= Position.row() && Position.row() < BOARD_SIZE
                && 0 <= Position.col() && Position.col() < BOARD_SIZE;
    }

    /**
     * Stores the previous wall's color.
     */
    public Square prevWallColor = Square.RED;

    /**
     * Moves the position of the player to given coordinates.
     * @param row number of the given row
     * @param col number of the given column
     */
    public void move(int row, int col) {
        if (isStartMove()) {
            Position position = new Position(startRow + 2, startCol);
        }

        Direction direction = StepDirection.of(row - Position.row(), col - Position.col());


        if (isValidDirection(row, col)) {
            prevWallColor = getNextWallColor(row, col);
            System.out.println(Position.row());
            System.out.println(Position.col());
            //board[Position.row()][Position.col()] = new ReadOnlyObjectWrapper<Square>(Square.NONE); //TODO
            Position position = new Position(row, col);
            System.out.println(position);
            board[row][col] = new ReadOnlyObjectWrapper<Square>(Square.POS);
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Checks if the direction of the movement is valid.
     * @param row the row of the next position
     * @param col the column of the next position
     * @return {@code true} if the movement is valid, {@code false} otherwise
     */
    public boolean isValidDirection(int row, int col) {

        if (isStartMove() && row == startRow && col == startCol) {
            return true;
        }
        if(!isStartMove()) {
            Square nextWallColor = getNextWallColor(row, col);
            if (prevWallColor.equals(Square.RED) && nextWallColor.equals(Square.WHITE)) {
                return true;
            }
            if (prevWallColor.equals(Square.WHITE) && nextWallColor.equals(Square.BLUE)) {
                return true;
            }
            if (prevWallColor.equals(Square.BLUE) && nextWallColor.equals(Square.RED)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the next wall's color.
     * @param row the row if the next position
     * @param col the column of the next position
     * @return the color of the next wall
     */
    public Square getNextWallColor(int row, int col) {
        Direction direction = StepDirection.of(row - Position.row(), col - Position.col());
        if (direction.equals(StepDirection.UP)) {
            return board[row + 1][col].getValue();
        }
        if (direction.equals(StepDirection.RIGHT)) {
            return board[row][col - 1].getValue();
        }
        if (direction.equals(StepDirection.DOWN)) {
            return board[row - 1][col].getValue();
        }
        if (direction.equals(StepDirection.LEFT)) {
            return board[row][col + 1].getValue();
        }

        return null; //TODO
    }

    /**
     * Checks if the move is the first move
     * @return {@code true} if it is the first move, {@code false} otherwise.
     */
    public boolean isStartMove() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (getSquare(i, j).equals(Square.POS)) {
                    return false;
                }
            }
        }
        return true;
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
        model.move(7, 3);
        System.out.println(model);
        model.move(5,3);
        System.out.println(model);
        model.move(3,3);
        System.out.println(model);
    }

}
