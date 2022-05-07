package ija.diagram.sequencediagram.controller;
import ija.diagram.ControllerMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WindowNotLoadController {

    @FXML
    private Button createClassButton;

    @FXML
    private Button notCreateButton;

    private ControllerMain controllerMain;
    private String name;

    public WindowNotLoadController(ControllerMain controllerMain, String name){
        this.controllerMain = controllerMain;
        this.name = name;
    }

    public void initialize(){
        createClassButton.addEventHandler(ActionEvent.ACTION, this::createModel);
        notCreateButton.addEventHandler(ActionEvent.ACTION, this::notCreateModel);
    }

    public void createModel(ActionEvent actionEvent){
        System.out.println("asfsdf");
    }

    public void notCreateModel(ActionEvent actionEvent){

    }


}
