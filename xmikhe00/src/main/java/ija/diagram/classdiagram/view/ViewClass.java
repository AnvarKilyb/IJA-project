package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.Arguments;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Item;
import ija.diagram.classdiagram.model.Methods;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class ViewClass extends VBox {

    private VBox arguments;
    private VBox methods;
    private TextField className = new TextField("Name"); //todo add setUserData
    private Label mainLabel = new Label("              ");
    private ContextMenu contextMenu = new ContextMenu();
    private Map<ArgumentItem, Arguments> argumentsMap = new HashMap<ArgumentItem, Arguments>();
    private Map<MethodItem, Methods> methodsMap = new HashMap<MethodItem, Methods>();

    public ViewClass(){
        super();
    }

    private void setVisualOptions(){
        super.setWidth(100);
        super.setHeight(100);
        super.setStyle("-fx-background-color: black;");
    }

    private void mainConstructClass(ClassContextController classContextController, ViewClassController viewClassController){
        this.addContextMenu(classContextController, viewClassController);
        mainLabel.setContextMenu(contextMenu);
        super.getChildren().add(mainLabel);
        super.getChildren().add(className);
        arguments = new VBox();
        methods = new VBox();
        arguments.setUserData(this);
        methods.setUserData(this);
        super.getChildren().add(arguments);
        super.getChildren().add(methods);
    }

    private void addContextMenu(ClassContextController classContextController, ViewClassController viewClassController){
        MenuItem menuItem1 = new MenuItem("Add new argument");
        menuItem1.setUserData(this);
        menuItem1.addEventHandler(Event.ANY, classContextController::addArgument);
        MenuItem menuItem2 = new MenuItem("Add new method");
        menuItem2.setUserData(this);
        menuItem2.addEventHandler(Event.ANY, classContextController::addMethod);
        MenuItem menuItem3 = new MenuItem("Delete class");
        menuItem3.setUserData(this);
        menuItem3.addEventHandler(Event.ANY, classContextController::deleteClass);
        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3);
    }

    public void show(ClassContextController classContextController, ViewClassController viewClassController){
        this.mainConstructClass(classContextController, viewClassController);
        this.setVisualOptions();
        super.addEventFilter(MouseEvent.MOUSE_DRAGGED, viewClassController::classDraggedMouse);
        super.addEventFilter(MouseEvent.MOUSE_PRESSED, viewClassController::clickToLine);
    }
//------------------------------------------------------------------------------------------------------//
//    public void addArgument(Arguments argument, ClassContextController classContextController){
//        ArgumentItem argumentItem = new ArgumentItem();
//        argumentItem.mainConstruct(classContextController);
//        arguments.getChildren().add(argumentItem);
//        argumentsMap.put(argumentItem, argument);
//    }
//    public void addMethod(Methods methods, ClassContextController classContextController){
//        MethodItem methodItem = new MethodItem();
//        methodItem.mainConstruct(classContextController);
//        methods.getChildren().add(methodItem);
//    }

    public void addArgument(Arguments argument, ClassContextController classContextController){
        ArgumentItem argumentItem = new ArgumentItem();
        argumentItem.mainConstruct(classContextController);
        argumentItem.setArgumentName(argument.getName());
        argumentItem.setArgumentType(argument.getType());
        String accessModifierString = Item.returnString(argument.getAccessModifier());
        argumentItem.setAccessModifier(accessModifierString);
        arguments.getChildren().add(argumentItem);
        argumentsMap.put(argumentItem, argument);
    }

    public void addMethod(Methods method ,ClassContextController classContextController){
        MethodItem methodItem = new MethodItem();
        methodItem.mainConstruct(classContextController);
        methodItem.setMethodName(method.getName());
        String accessModifierString = Item.returnString(method.getAccessModifier());
        methodItem.setAccessModifier(accessModifierString);
        methods.getChildren().add(methodItem);
        methodsMap.put(methodItem, method);
    }

    public void deleteArgument(ArgumentItem argumentItem){
        argumentsMap.remove(argumentItem);
    }

    public void deleteMethod(MethodItem argumentItem){
        methodsMap.remove(argumentItem);
    }

    public Arguments returnArgument(ArgumentItem argumentItem){
        return argumentsMap.get(argumentItem);
    }

    public Methods returnMethod(MethodItem methodItem){
        return  methodsMap.get(methodItem);
    }

    public void setX(double x){
        super.setLayoutX(x);
    }

    public void setY(double y){
        super.setLayoutY(y);
    }

    public void setClassName(String className){
        this.className.setText(className);
    }
}
