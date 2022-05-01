package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import javafx.event.Event;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Reprezentuje zobrazení metod ve třídě
 * obsahuje metody pro vytvoření a změnu metod
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class MethodItem extends HBox implements ViewItem{

    private final ChoiceBox<String> accessModifier = new ChoiceBox<String>();
    private TextField argumentName = new TextField();
    ContextMenu contextMenu = new ContextMenu();

    public MethodItem(){
        super();
        accessModifier.getItems().add("-");
        accessModifier.getItems().add("+");
        accessModifier.getItems().add("#");
        accessModifier.getItems().add("~");
    }

    public void mainConstruct(ClassContextController classContextController){
        super.getChildren().add(accessModifier);
        super.getChildren().add(argumentName);
        initContextMenu(classContextController);
        accessModifier.setContextMenu(contextMenu);
        argumentName.setContextMenu(contextMenu);
        argumentName.setPrefWidth(212); //todo normal
    }

    private void initContextMenu(ClassContextController classContextController){
        MenuItem menuItem1 = new MenuItem("Delete");
        menuItem1.setUserData(this);
        menuItem1.addEventHandler(Event.ANY, classContextController::deleteMethod);
        contextMenu.getItems().add(menuItem1);
    }


    public void setMethodName(String argumentName){
        this.argumentName.setText(argumentName);
    }

    public void setAccessModifier(String accessModifier){
        this.accessModifier.setValue(accessModifier);
    }


    public VBox getParentItem(){
        return (VBox) this.getParent();
    }

    public ChoiceBox<String> getAccessModifier() {
        return accessModifier;
    }

    public TextField getArgumentName() {
        return argumentName;
    }
}
