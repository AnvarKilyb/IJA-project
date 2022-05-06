package ija.diagram.sequencediagram.controller;
import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.sequencediagram.model.SObject;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import ija.diagram.sequencediagram.view.ViewObject;
import ija.diagram.sequencediagram.view.ViewSequenceDiagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ObjectNotExistController {
    @FXML
    private Button createObjectButton;

    @FXML
    private Button notCreateButton;

    private String name;
    private ControllerMain controllerMain;

    public  ObjectNotExistController(ControllerMain controllerMain, String name){
        this.controllerMain = controllerMain;
        this.name = name;
    }

    public void initialize(){
       createObjectButton.addEventHandler(ActionEvent.ACTION, this::createObject);
       notCreateButton.addEventHandler(ActionEvent.ACTION, this::notCreateObject);
    }


    public void createObject(ActionEvent actionEvent){
        Stage stage = (Stage)createObjectButton.getScene().getWindow();
        SequenceDiagram sequenceDiagram = controllerMain.getSequenceDiagram();
        ViewSequenceDiagram viewSequenceDiagram = controllerMain.getViewSequenceDiagram();
        DClass dClass = controllerMain.getClassDiagram().returnClass(this.name);
        if(dClass == null){
            controllerMain.writeLabelWarning("The object cannot be created \nbecause the class does not exist.");
            return;
        }
        SObject sObject = sequenceDiagram.addObject(dClass);
        ViewObject viewObject = viewSequenceDiagram.addNewObject(sObject);
        ContextObjectController contextObjectController = controllerMain.getContextObjectController();
        viewObject.addEventHandler(MouseEvent.MOUSE_CLICKED, contextObjectController::addNewMessage);
        controllerMain.getSequencePane().getChildren().add(viewObject);
        stage.close();
    }

    public void notCreateObject(ActionEvent actionEvent){
        Stage stage = (Stage)notCreateButton.getScene().getWindow();
        stage.close();
    }
}
