package ija.diagram;
import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.view.ViewDiagram;
import ija.diagram.sequencediagram.controller.ContextObjectController;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import ija.diagram.sequencediagram.view.ViewSequenceDiagram;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hlavní soubor pro projekt predmětu IJA
 * Hlavni soubor pro spuštění programu
 * Obsahuje metodu:
 * "start" a "main"
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Main extends Application {


    /**
     *Metoda main vytváří okno uživatelského rozhraní,
     * slouží bufferem pro zavádění instance ovladač{@link ControllerMain} a hlavní třídy pro model {@link ClassDiagram} a view {@link ViewDiagram}
     */
    @Override
    public void start(Stage stage) throws IOException {
        ClassDiagram classDiagram = new ClassDiagram();
        ViewDiagram viewDiagram = new ViewDiagram();
        SequenceDiagram sequenceDiagram = new SequenceDiagram();
        ViewSequenceDiagram viewSequenceDiagram = new ViewSequenceDiagram();
        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(
//            new FileChooser.ExtensionFilter("JSON file","*.json")
//        );
        ControllerMain controllerMain = new ControllerMain(classDiagram, viewDiagram, sequenceDiagram, viewSequenceDiagram, fileChooser, stage);
        viewDiagram.setControllerMain(controllerMain);
        viewSequenceDiagram.setControllerMain(controllerMain);
        ViewClassController viewClassController = new ViewClassController(controllerMain);
        ClassContextController classContextController = new ClassContextController(controllerMain);
        controllerMain.setClassContextController(classContextController);
        controllerMain.setViewClassController(viewClassController);
        ContextObjectController contextObjectController = new ContextObjectController(controllerMain);
        controllerMain.setContextObjectController(contextObjectController);


        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/main.fxml"));
        loader.setController(controllerMain);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda main je hlavní metodou, spustí program
     * @param args argumenty
     */
    public static void main(String[] args) {
        launch();
    }

}