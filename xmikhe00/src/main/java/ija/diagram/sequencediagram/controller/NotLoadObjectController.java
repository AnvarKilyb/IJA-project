package ija.diagram.sequencediagram.controller;

import ija.diagram.ControllerMain;
import ija.diagram.sequencediagram.view.ViewObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NotLoadObjectController {

    private ControllerMain controllerMain;
    private String name;

    public NotLoadObjectController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    public void addModel(MouseEvent mouseEvent){
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            ViewObject viewObject = (ViewObject) mouseEvent.getSource();
            FXMLLoader loader = new FXMLLoader(ContextObjectController.class.getResource("/main/notLoad.fxml"));
            WindowNotLoadController windowNotLoadController = new WindowNotLoadController(controllerMain,name);
            loader.setController(windowNotLoadController);
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage createNewMessage = new Stage();
            createNewMessage.setUserData(viewObject);
            createNewMessage.setScene(scene);
            createNewMessage.initModality(Modality.APPLICATION_MODAL);
            createNewMessage.setResizable(false);
            createNewMessage.showAndWait();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
