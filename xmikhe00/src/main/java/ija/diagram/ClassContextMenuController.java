package ija.diagram;

import javafx.event.Event;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClassContextMenuController {

    public static void item1(Event mouseEvent){
        System.out.println("1111111");
        MenuItem menuItem = (MenuItem) mouseEvent.getSource();
        ViewClass viewClass = (ViewClass) menuItem.getUserData();
        viewClass.addArgument();
//        System.out.println(a.getUserData());
//        if(mouseEvent.getEventTypegetButton() == MouseButton.PRIMARY){
//            System.out.println(mouseEvent.getSource());
//        }
    }
}
