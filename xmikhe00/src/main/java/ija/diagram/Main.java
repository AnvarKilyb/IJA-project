package ija.diagram;


import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ControllerMain;
import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.view.ViewDiagram;
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
        ClassDiagram classDiagram = new ClassDiagram();
        ViewDiagram viewDiagram = new ViewDiagram();
        ControllerMain controllerMain = new ControllerMain(classDiagram, viewDiagram);
        viewDiagram.setControllerMain(controllerMain);
        ViewClassController viewClassController = new ViewClassController(controllerMain);
        ClassContextController classContextController = new ClassContextController(controllerMain);
        controllerMain.setClassContextController(classContextController);
        controllerMain.setViewClassController(viewClassController);

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