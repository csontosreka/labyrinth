package model;

/**
 * An enum representing the directions.
 */
public enum StepDirection implements Direction{

    /**
     * Four possible direction.
     */
    UP(-2, 0),
    DOWN(2, 0),
    LEFT(0, -2),
    RIGHT(0, 2);

    private final int rowChange;
    private final int colChange;


    public int getRowChange() {
        return rowChange;
    }

    public int getColChange() {
        return colChange;
    }

    StepDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * Moves the marker in given direction.
     * @param rowChange change of the row
     * @param colChange change of the column
     * @return the direction of movement
     */
    public static StepDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        System.out.println(of(-2, 0));
    }
}
