package controller;

import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.LabyrinthModel;
import model.Square;
import org.tinylog.Logger;

public class LabyrinthController {


    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @FXML
    private GridPane board;

    private LabyrinthModel model = new LabyrinthModel();

    @FXML
    private void initialize() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        if (model.getSquare(i, j) == Square.X) {
            square.getChildren().add(new Rectangle(10.4, 10.4, Color.GHOSTWHITE));
        }
        if (model.getSquare(i, j) == Square.NONE) {
            square.getChildren().add(new Rectangle(137.6, 136.8, Color.LIGHTGRAY));
            square.setOnMouseClicked(this::handleMouseClick);
        }
        if (model.getSquare(i, j) == Square.BLACK) {
            if (i % 2 == 0) {
                square.getChildren().add(new Rectangle(137.6, 10.4, Color.BLACK));
            } else {
                square.getChildren().add(new Rectangle(10.4, 136.8, Color.BLACK));
            }
        }
        if (model.getSquare(i, j) == Square.BLUE) {
            if (i % 2 == 0) {
                square.getChildren().add(new Rectangle(137.6, 10.4, Color.BLUE));
            } else {
                square.getChildren().add(new Rectangle(10.4, 136.8, Color.BLUE));
            }
        }
        if (model.getSquare(i, j) == Square.RED) {
            if (i % 2 == 0) {
                square.getChildren().add(new Rectangle(137.6, 10.4, Color.RED));
            } else {
                square.getChildren().add(new Rectangle(10.4, 136.8, Color.RED));
            }
        }
        if (model.getSquare(i, j) == Square.WHITE) {
            if (i % 2 == 0) {
                square.getChildren().add(new Rectangle(137.6, 10.4, Color.WHITE));
            } else {
                square.getChildren().add(new Rectangle(10.4, 136.8, Color.WHITE));
            }
        }

        var piece = new Circle(50);

        piece.fillProperty().bind(
                new ObjectBinding<Paint>() {
                    {
                        super.bind(model.squareProperty(i, j));
                    }

                    @Override
                    protected Paint computeValue() {
                        return switch (model.squareProperty(i, j).get()) {
                            case NONE, X, BLUE, BLACK, RED, WHITE -> Color.TRANSPARENT;
                            case POS -> Color.BLACK;
                        };
                    }
                }
        );
        square.getChildren().add(piece);

        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Logger.debug("Click on square ({},{})", row, col);
        model.move(row, col);
        initialize();
    }
}
