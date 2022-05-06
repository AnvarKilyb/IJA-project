package ija.diagram.sequencediagram.controller;
import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Methods;
import ija.diagram.sequencediagram.model.ActivationBox;
import ija.diagram.sequencediagram.model.Message;
import ija.diagram.sequencediagram.model.SObject;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import ija.diagram.sequencediagram.view.ViewActiveBox;
import ija.diagram.sequencediagram.view.ViewMessage;
import ija.diagram.sequencediagram.view.ViewObject;
import ija.diagram.sequencediagram.view.ViewSequenceDiagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

import static ija.diagram.sequencediagram.model.Message.convertType;

public class CreateMessageController {

    @FXML
    private RadioButton Asynchronous;

    @FXML
    private RadioButton Delete;

    @FXML
    private RadioButton Synchronous;

    @FXML
    private Button createMessage;

    @FXML
    private TextField messageReply;

    @FXML
    private TextField objectName;

    private ControllerMain controllerMain;

    private ToggleGroup messageGroup = new ToggleGroup();


    public CreateMessageController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    public void initialize(){
        Asynchronous.setToggleGroup(messageGroup);
        Synchronous.setToggleGroup(messageGroup);
        Delete.setToggleGroup(messageGroup);
        Synchronous.fire();
        createMessage.addEventHandler(ActionEvent.ACTION, this::createNewMessage);
    }


    public void createNewMessage(ActionEvent actionEvent){
        Stage stage = (Stage)createMessage.getScene().getWindow();
        SequenceDiagram sequenceDiagram = controllerMain.getSequenceDiagram();
        ViewSequenceDiagram viewSequenceDiagram = controllerMain.getViewSequenceDiagram();

        ViewObject viewObjectLeft = (ViewObject) stage.getUserData();
        SObject sObjectLeft = viewSequenceDiagram.returnObject(viewObjectLeft);
        ActivationBox activationBoxLeft = sObjectLeft.getActivationBox();
        ViewActiveBox viewActiveBoxLeft = viewObjectLeft.getViewActiveBox();
        if(activationBoxLeft == null){
            activationBoxLeft = sObjectLeft.addActiveBox();
            viewActiveBoxLeft = viewObjectLeft.addViewActionBox(activationBoxLeft);
            viewObjectLeft.getChildren().add(viewActiveBoxLeft);
        }

        String name  = objectName.getText();
        String messageName = messageReply.getText();
        SObject sObjectRight = sequenceDiagram.getObject(name);
        if(sObjectRight == null){
            return;
        }
        ViewObject viewObjectRight = viewSequenceDiagram.getViewObject(sObjectRight);
        ActivationBox activationBoxRight = sObjectRight.getActivationBox();
        ViewActiveBox viewActiveBoxRight = viewObjectRight.getViewActiveBox();

        if(activationBoxRight == null){
            activationBoxRight = sObjectRight.addActiveBox();
            viewActiveBoxRight = viewObjectRight.addViewActionBox(activationBoxRight);
            viewObjectRight.getChildren().add(viewActiveBoxRight);
        }

        DClass dClass = controllerMain.getClassDiagram().returnClass(name);
        List<Methods> methodsList = dClass.getMethodsList();
        Methods method = null;

        for(Methods m : methodsList){
            if(m.getName().equals(messageName)){
                method = m;
            }
        }

        if(method == null){
            System.out.println("Todo");
        }

        RadioButton selectedRadioButton = (RadioButton) messageGroup.getSelectedToggle();

        Message message = activationBoxLeft.addNewOutMessage(messageName, convertType(selectedRadioButton.getText()));
        activationBoxRight.addNewInMessage(message);
        ViewMessage viewMessage = viewActiveBoxLeft.addViewMessage(message);
        viewActiveBoxLeft.setPrefHeight(activationBoxLeft.getHeight());
        viewActiveBoxLeft.getChildren().add(viewMessage);

//        activationBoxRight.setHeight(activationBoxLeft.getHeight());
        viewActiveBoxRight.setPrefHeight(activationBoxRight.getHeight());
    }
}
