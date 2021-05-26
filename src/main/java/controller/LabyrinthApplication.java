package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LabyrinthApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ui.fxml"));
        stage.setTitle("Labyrinth");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /*
    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("labyrinth"));
                    bind(GameResultDao.class);
                }
            }
    ));

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws Exception {
        Logger.info("Starting application");
        context.init();
        fxmlLoader.setLocation(getClass().getResource("/fxml/opening.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Labyrinth");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
     */
}
