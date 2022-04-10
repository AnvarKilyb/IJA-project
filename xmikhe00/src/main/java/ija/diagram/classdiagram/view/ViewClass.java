package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ViewClassController;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ViewClass extends VBox {

    private VBox arguments;
    private VBox methods;
    ContextMenu contextMenu = new ContextMenu();

    public ViewClass(){
        super();
    }

    public void show(){
        Label label = new Label("Class");
        this.addContextMenu();
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
//        ClassItem item = new ClassItem();
//        item.show();
//        arguments.getChildren().add(item);
        //todo add interception click right mouses button
        super.addEventFilter(MouseEvent.MOUSE_DRAGGED, ViewClassController::classDraggedMouse);
//        super.addEventFilter(MouseEvent.MOUSE_CLICKED, ViewClassController::classMenu);
    }

    private void addContextMenu(){
        MenuItem menuItem1 = new MenuItem("Add new argument");
        menuItem1.setUserData(this);
        menuItem1.addEventHandler(Event.ANY, ClassContextController::add_item);
        MenuItem menuItem2 = new MenuItem("Add new method");

        contextMenu.getItems().addAll(menuItem1,menuItem2);

    }

    public void addArgument(){
        ClassItem classItem = new ClassItem();
        classItem.show(this);
        arguments.getChildren().add(classItem);
    }

}
