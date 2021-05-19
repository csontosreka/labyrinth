package model;

import java.util.Objects;

/**
 * Represents the position of the players marker.
 */
public final class Position {
    private final int row;
    private final int col;

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

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Position) obj;
        return this.row == that.row &&
                this.col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

}
