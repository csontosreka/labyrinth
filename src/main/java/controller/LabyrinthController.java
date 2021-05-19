package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.LabyrinthModel;

public class LabyrinthController {
    @FXML
    private GridPane board;

    private String playerName;
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private LabyrinthModel model = new LabyrinthModel();

    @FXML
    private void initialize() {
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private Circle createPiece() {
        var piece = new Circle(50);
        piece.setFill(Color.BLACK);
        return piece;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        System.out.printf("Click on square (%d,%d)\n", row, col);
        //model.move(row, col);
    }
}
