package ija.diagram.classdiagram.controller;

import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewItem;
import javafx.event.Event;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class ClassContextController {

    public static void addArgument(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        viewClass.addArgument();
    }

    public static void addMethod(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        viewClass.addMethod();
    }

    public static void deleteItem(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewItem viewItem = (ViewItem) menuItem.getUserData();
        VBox vBox = (VBox) viewItem.getParentItem();
//        System.out.println("sdsd");
        vBox.getChildren().remove(viewItem);
    }

    public static void show_contextmenu_arguments(Event mouseEvent){

    }
}
