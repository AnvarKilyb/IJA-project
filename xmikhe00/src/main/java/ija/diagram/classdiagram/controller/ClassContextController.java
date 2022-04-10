package ija.diagram.classdiagram.controller;

import ija.diagram.classdiagram.view.ClassItem;
import ija.diagram.classdiagram.view.ViewClass;
import javafx.event.Event;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class ClassContextController {

    public static void add_item(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        viewClass.addArgument();
    }

    public static void delete_item(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ClassItem classItem = (ClassItem) menuItem.getUserData();
        VBox vBox = (VBox) classItem.getParent();
//        System.out.println("sdsd");
        vBox.getChildren().remove(classItem);
    }

    public static void show_contextmenu_arguments(Event mouseEvent){

    }
}
