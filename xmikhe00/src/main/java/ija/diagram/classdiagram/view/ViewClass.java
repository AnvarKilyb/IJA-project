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
    private TextField className = new TextField("Name");
    private Label mainLabel = new Label("              ");
    ContextMenu contextMenu = new ContextMenu();

    public ViewClass(){
        super();
    }

    private void setVisualOptions(){
        super.setWidth(100);
        super.setHeight(100);
        super.setStyle("-fx-background-color: black;");
    }

    private void mainConstructClass(){
        this.addContextMenu();
        mainLabel.setContextMenu(contextMenu);
        super.getChildren().add(mainLabel);
        super.getChildren().add(className);
        arguments = new VBox();
        methods = new VBox();
        super.getChildren().add(arguments);
        super.getChildren().add(methods);
    }

    private void addContextMenu(){
        MenuItem menuItem1 = new MenuItem("Add new argument");
        menuItem1.setUserData(this);
        menuItem1.addEventHandler(Event.ANY, ClassContextController::addArgument);
        MenuItem menuItem2 = new MenuItem("Add new method");
        menuItem2.setUserData(this);
        menuItem2.addEventHandler(Event.ANY, ClassContextController::addMethod);
        contextMenu.getItems().addAll(menuItem1,menuItem2);
    }

    public void show(){
        this.mainConstructClass();
        this.setVisualOptions();
        super.addEventFilter(MouseEvent.MOUSE_DRAGGED, ViewClassController::classDraggedMouse);
    }
//------------------------------------------------------------------------------------------------------//
    public void addArgument(){
        ArgumentItem argumentItem = new ArgumentItem();
        argumentItem.mainConstruct();
        arguments.getChildren().add(argumentItem);
        System.out.println(className.getPrefWidth());
    }
    public void addMethod(){
        MethodItem methodItem = new MethodItem();
        methodItem.mainConstruct();
        methods.getChildren().add(methodItem);
    }

    public void addArgument(String argumentName, String accessModifier, String typeName){
        ArgumentItem argumentItem = new ArgumentItem();
        argumentItem.mainConstruct();
        argumentItem.setArgumentName(argumentName);
        argumentItem.setArgumentType(typeName);
        argumentItem.setAccessModifier(accessModifier);
        arguments.getChildren().add(argumentItem);

    }

    public void addMethod(String methodName, String accessModifier){
        MethodItem methodItem = new MethodItem();
        methodItem.mainConstruct();
        methodItem.setMethodName(methodName);
        methodItem.setAccessModifier(accessModifier);
        methods.getChildren().add(methodItem);
    }

    public void setX(int x){
        super.setLayoutX(x);
    }

    public void setY(int y){
        super.setLayoutY(y);
    }

    public void setClassName(String className){
        this.className.setText(className);
    }
}
