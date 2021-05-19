package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A class representing the players marker and it's position on the board.
 */
public class Piece {
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    public Piece(Position position) {
        this.position.set(position);
    }

    public Position getPosition() {
        return position.get();
    }

    /**
     * Moves the marker to the new direction.
     * @param direction coordinates of the given direction
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    public String toString() {
        return position.get().toString();
    }

    public static void main(String[] args) {
        Piece piece = new Piece(new Position(7, 3));
        piece.positionProperty().addListener((observableValue, oldPosition, newPosition) -> {
            System.out.printf("%s -> %s\n", oldPosition.toString(), newPosition.toString());
        });
        System.out.println(piece);
        piece.moveTo(StepDirection.UP);
        System.out.println(piece);
    }
}
