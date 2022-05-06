package ija.diagram.sequencediagram.controller;


import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.sequencediagram.model.SObject;
import ija.diagram.sequencediagram.view.ViewObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddObjectController {
    @FXML
    private Button createObject;

    @FXML
    private TextField objectName;


    ControllerMain controllerMain;

    public void initialize(){
        createObject.addEventHandler(ActionEvent.ACTION,this::addObject);
    }

    public void addObject(ActionEvent actionEvent){
        Stage stage = (Stage)createObject.getScene().getWindow();
        String name = objectName.getText();
        ClassDiagram classDiagram = controllerMain.getClassDiagram();
        DClass dClass = classDiagram.returnClass(name);
        if(dClass == null){
            FXMLLoader loader = new FXMLLoader(AddObjectController.class.getResource("/main/classNotExist.fxml"));
            ClassNotExistController classNotExistController = new ClassNotExistController(controllerMain, name);
            loader.setController(classNotExistController);
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage addClass = new Stage();
            addClass.setScene(scene);
            addClass.initModality(Modality.APPLICATION_MODAL);
            addClass.setResizable(false);
            addClass.showAndWait();
        }

        dClass = classDiagram.returnClass(name);
        if(dClass == null){
            stage.close();
            return;
        }

       SObject sObject = controllerMain.getSequenceDiagram().addObject(dClass);
       if(sObject == null){
           return;
       }
       ViewObject viewObject = controllerMain.getViewSequenceDiagram().addNewObject(sObject);
       ContextObjectController contextObjectController = controllerMain.getContextObjectController();
       viewObject.addEventHandler(MouseEvent.MOUSE_CLICKED,contextObjectController::addNewMessage);
       Pane mainPane = controllerMain.getSequencePane();
       mainPane.getChildren().add(viewObject);
       stage.close();
    }

    public void setControllerMain(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

}
