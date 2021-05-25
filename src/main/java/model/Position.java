package model;

import java.util.Objects;

/**
 * Represents the position of the players marker.
 */
public final class Position {
    private static int row;
    private static int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Moves the marker to given direction.
     * @param direction new direction
     * @return changed coordinates
     */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

    public static int row() {
        return row;
    }

    public static int col() {
        return col;
    }

}
