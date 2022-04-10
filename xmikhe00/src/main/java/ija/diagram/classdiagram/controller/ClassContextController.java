package ija.diagram.classdiagram.controller;

import ija.diagram.classdiagram.view.ViewClass;
import javafx.event.Event;
import javafx.scene.control.MenuItem;

public class ClassContextController {

    public static void add_item(Event mouseEvent){
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        viewClass.addArgument();
    }

    public static void delete_item(Event mouseEvent){

    }

    public static void show_contextmenu_arguments(Event mouseEvent){

    }
}
