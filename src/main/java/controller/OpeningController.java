package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import org.tinylog.Logger;

public class OpeningController {

    @FXML
    private TextField playerName;


    @FXML
    private void handleStartButton(ActionEvent event) throws IOException {
        Logger.info("Name entered: {}", playerName.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = fxmlLoader.load();
        LabyrinthController controller = fxmlLoader.<LabyrinthController>getController();
        controller.setPlayerName(playerName.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
