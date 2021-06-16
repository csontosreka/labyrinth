package controller;

import helper.ControllerHelper;
import helper.Stopwatch;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.LabyrinthModel;
import model.Square;
import org.tinylog.Logger;
//import result.GameResult;
//import result.GameResultDao;


import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class LabyrinthController {

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private Label timeLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button giveUpButton;

    @FXML
    private Label nextWallColorLabel;

    @FXML
    private Label messageLabel;

    private String playerName;


    //@Inject
    //private GameResultDao gameResultDao;

    public void setPlayerName(String playerName) {
        Logger.info("Setting name to {}", playerName);
        this.playerName = playerName;
    }

    @FXML
    private GridPane board;

    private LabyrinthModel model = new LabyrinthModel();

    private Stopwatch stopwatch = new Stopwatch();

    private Instant startTime;

    @FXML
    private void initialize() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        startTime = Instant.now();
        timeLabel.textProperty().bind(stopwatch.hhmmssProperty());
        Platform.runLater(() -> messageLabel.setText(String.format("Good luck, %s!", playerName)));
        stopwatch.start();
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

        if(!model.isSolved()){
            model.move(row, col);
            initialize();
        } else {
            stopwatch.stop();
            messageLabel.setText(String.format("Congratulations, %s!", playerName));
        }

    }

    public void handleResetButton(ActionEvent actionEvent)  {
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        Logger.info("Resetting game");
        stopwatch.stop();
        resetGame();
    }

    private void resetGame(){
        model = new LabyrinthModel();
        initialize();
        startTime = Instant.now();
        if (stopwatch.getStatus() == Animation.Status.PAUSED) {
            stopwatch.reset();
        }
        stopwatch.start();
    }

/*    private GameResult createGameResult() {
        return GameResult.builder()
                .player(playerName)
                .duration(Duration.between(startTime, Instant.now()))
                .build();
    }
*/
    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {

        /*
        var buttonText = ((Button) actionEvent.getSource()).getText();
        Logger.debug("{} is pressed", buttonText);
        if (buttonText.equals("Give Up")) {
            stopwatch.stop();
            Logger.info("The game has been given up");
        }
        Logger.debug("Saving result");
        gameResultDao.persist(createGameResult());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerHelper.loadAndShowFXML(fxmlLoader, "/fxml/opening.fxml", stage);
        */

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/opening.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
