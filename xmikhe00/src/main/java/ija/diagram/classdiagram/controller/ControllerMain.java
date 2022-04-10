package ija.diagram.classdiagram.controller;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.loader.objectJSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static ija.diagram.loader.parser.parseJSON;

public class ControllerMain {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonLoad;

    @FXML
    private Button buttonSave;

    @FXML
    private Pane mainPane;
//    private AnchorPane mainPane;

    public void initialize(){
        buttonAdd.addEventFilter(ActionEvent.ACTION,this::addClassAction);
        buttonLoad.addEventFilter(ActionEvent.ACTION,this::loadFile);
    }

    private void addClassAction(ActionEvent event){
        ViewClass viewclass = new ViewClass();
        viewclass.show();
        this.mainPane.getChildren().add(viewclass);
    }

    private void loadFile(ActionEvent event){
        ArrayList<objectJSON> list = new ArrayList<>();
        list = parseJSON();
//        System.out.println(list.get(1).getName());
        for (objectJSON object : list){


        }

    }

}
