package ija.diagram;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

public class ViewClass extends VBox {

    private VBox arguments;
    private VBox methods;
    ContextMenu contextMenu = new ContextMenu();

    public ViewClass(){
        super();

        Label label = new Label("Class");
        this.addCotextMenu();
        label.setContextMenu(contextMenu);
        super.getChildren().add(label);
        super.getChildren().add(new TextField("Name"));
        super.setWidth(100);
        super.setHeight(100);
        super.setStyle("-fx-background-color: black;");
        arguments = new VBox();
        methods = new VBox();
        super.getChildren().add(arguments);
        super.getChildren().add(methods);
        ClassItem item = new ClassItem();
        arguments.getChildren().add(item);
        //todo add interception click right mouses button
        super.addEventFilter(MouseEvent.MOUSE_DRAGGED,ViewClassController::classDraggedMouse);
//        super.addEventFilter(MouseEvent.MOUSE_CLICKED, ViewClassController::classMenu);
    }

    public void write(){
        System.out.println("Hello, I am ViewClass");
    }


    private void addCotextMenu(){
        MenuItem menuItem1 = new MenuItem("Add new argument");
        menuItem1.setUserData(this);
        menuItem1.addEventHandler(Event.ANY,ClassContextMenuController::item1);
        MenuItem menuItem2 = new MenuItem("Add new method");

        contextMenu.getItems().addAll(menuItem1,menuItem2);

    }

    public void addArgument(){
        arguments.getChildren().add(new ClassItem());
    }

}
