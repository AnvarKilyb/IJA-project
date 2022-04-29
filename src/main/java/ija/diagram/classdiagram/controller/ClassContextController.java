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

/**
 * Ovladač pro kontextovou nabídku třídy
 * obsahuje metody pro vytvoření a odstranění metod, argumentů a odstranění třídy
 * @author Vladislav Mikheda : xmikhe0
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ClassContextController {
    /**
     * Instance hlavního ovladače
     */
    private final ControllerMain controllerMain;

    public ClassContextController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    /**
     * Přidává argument do třídy
     * @param mouseEvent akce využívání kontextové nabídky pro dodání argumentu
     */
    public void addArgument(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        Arguments argument = dClass.addArgument();
        controllerMain.getViewDiagram().addArguments(viewClass,argument);
    }

    /**
     * Přidává metod do třídy
     * @param mouseEvent akce využívání kontextové nabídky pro dodání metod
     */
    public void addMethod(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        DClass dClass = controllerMain.getViewDiagram().returnDClass(viewClass);
        Methods method = dClass.addMethod();
        controllerMain.getViewDiagram().addMethods(viewClass,method);
    }

    /**
     * Odstraní argument z třídy
     * @param mouseEvent akce využívaní kontextové nabídky pro odstranění argumentu
     */
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

    /**
     * Odstraní metodu z třídy
     * @param mouseEvent akce využívaní kontextové nabídky pro odstranění metod
     */
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

    /**
     * Odstraní třídu
     * @param mouseEvent akce využívaní kontextové nabídky pro odstranění třídy
     */
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
}
