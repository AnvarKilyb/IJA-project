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

public class ContextObjectController {
    private ControllerMain controllerMain;
    public ContextObjectController(ControllerMain controllerMain)
    {
        this.controllerMain = controllerMain;
    }

    public void addNewMessage(MouseEvent mouseEvent){
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            ViewObject viewObject = (ViewObject) mouseEvent.getSource();
            FXMLLoader loader = new FXMLLoader(ContextObjectController.class.getResource("/main/createMessage.fxml"));
            CreateMessageController createMessageController = new CreateMessageController(controllerMain);
            loader.setController(createMessageController);
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

}