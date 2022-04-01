package ija.diagram;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ControllerMain controllerMain = new ControllerMain();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/main.fxml"));
        loader.setController(controllerMain);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();







    }

    public static void main(String[] args) {
        launch();
    }

}