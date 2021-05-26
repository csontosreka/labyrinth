package model;

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
