package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import javafx.event.Event;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class TextItem extends TextField {

    ContextMenu contextMenu = new ContextMenu();

    public TextItem(){
        super();
    }

    public void addContextMenu(ClassItem classLink){
        MenuItem menuItem1 = new MenuItem("Delete");
        menuItem1.setUserData(classLink);
        menuItem1.addEventHandler(Event.ANY, ClassContextController::delete_item);
        contextMenu.getItems().add(menuItem1);
        super.setContextMenu(contextMenu);
    }
}
