package ija.diagram.classdiagram.controller;

import ija.diagram.classdiagram.model.Arguments;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Methods;
import ija.diagram.classdiagram.view.*;
import javafx.event.Event;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClassContextController {
    private final ControllerMain controllerMain;

    public ClassContextController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    public void addArgument(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        Arguments argument = dClass.addArgument();
        controllerMain.getViewDiagram().addArguments(viewClass,argument);
    }

    public void addMethod(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        Methods method = dClass.addMethod();
        controllerMain.getViewDiagram().addMethods(viewClass,method);
    }

    public void deleteArgument(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ArgumentItem argumentItem = (ArgumentItem) menuItem.getUserData();
        VBox vBox = (VBox) argumentItem.getParentItem();
        ViewClass viewClass = (ViewClass) vBox.getUserData();
        Arguments argument = viewClass.returnArgument(argumentItem);
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        dClass.argumentDelete(argument);
        viewClass.deleteArgument(argumentItem);
        vBox.getChildren().remove(argumentItem);
    }

    public void deleteMethod(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        MethodItem methodItem = (MethodItem) menuItem.getUserData();
        VBox vBox = (VBox) methodItem.getParentItem();
        ViewClass viewClass = (ViewClass) vBox.getUserData();
        Methods method = viewClass.returnMethod(methodItem);
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        dClass.methodDelete(method);
        viewClass.deleteMethod(methodItem);
        vBox.getChildren().remove(methodItem);
    }

    public void deleteClass(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        Pane mainPane = (Pane) viewClass.getParent();
        ViewDiagram viewDiagram = controllerMain.getViewDiagram();
        DClass dClass = viewDiagram.returnDClass(viewClass);
        ClassDiagram classDiagram = controllerMain.getClassDiagram();
        classDiagram.classDelete(dClass);
        viewDiagram.deleteClass(viewClass);
        mainPane.getChildren().remove(viewClass);
    }

    public static void show_contextmenu_arguments(Event mouseEvent){

    }
}
