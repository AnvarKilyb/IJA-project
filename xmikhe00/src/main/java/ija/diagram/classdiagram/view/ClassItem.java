package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class ClassItem extends HBox {

    private final ChoiceBox<String> relationShip = new ChoiceBox<String>();
    private TextItem argumentName = new TextItem();
    private TextItem argumentType = new TextItem();
    ContextMenu contextMenu = new ContextMenu();

    public ClassItem(){
        super();
        relationShip.getItems().add("-");
        relationShip.getItems().add("+");
        relationShip.getItems().add("#");
        relationShip.getItems().add("~");
        argumentType.setPrefWidth(50);
    }

    public void show(ViewClass classLink){
        super.getChildren().add(relationShip);
        super.getChildren().add(argumentName);
        super.getChildren().add(argumentType);
        addContextMenu(classLink);
    }

    private void addContextMenu(ViewClass classLinc){
        argumentName.addContextMenu(classLinc);
        argumentType.addContextMenu(classLinc);
    }

    public ChoiceBox<String> getChoiceBox() {
        return relationShip;
    }

    public TextField getArgumentName() {
        return argumentName;
    }

    public TextField getArgumentType() {
        return argumentType;
    }
}
