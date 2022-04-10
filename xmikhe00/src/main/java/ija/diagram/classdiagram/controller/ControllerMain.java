package ija.diagram.classdiagram.controller;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewDiagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ControllerMain {
    private ViewClassController viewClassController;
    private ClassContextController classContextController;
    private final ClassDiagram classDiagram;
    private final ViewDiagram viewDiagram;

    public ControllerMain(ClassDiagram classDiagram, ViewDiagram viewDiagram){
            this.classDiagram = classDiagram;
            this.viewDiagram = viewDiagram;
    }

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonLoad;

    @FXML
    private Button buttonSave;

    @FXML
    private Pane mainPane;
//    private AnchorPane mainPane;

    public void initialize(){
        buttonAdd.addEventFilter(ActionEvent.ACTION,this::addClassAction);
        buttonLoad.addEventFilter(ActionEvent.ACTION,this::loadFile);
    }

    private void addClassAction(ActionEvent event){
        DClass dClass = classDiagram.addClass();
        ViewClass viewClass = viewDiagram.addNewClass(dClass);
        this.mainPane.getChildren().add(viewClass);
    }

    private void loadFile(ActionEvent event){
        classDiagram.writeAll();
//        ArrayList<objectJSON> list = new ArrayList<>();
//        list = parseJSON();
////        System.out.println(list.get(1).getName());
//        for (objectJSON object : list){
//
//
//        }
    }


    public ViewClassController getViewClassController(){
        return viewClassController;
    }

    public ClassContextController getClassContextController(){
        return classContextController;
    }

    public void setClassContextController(ClassContextController classContextController){
        this.classContextController = classContextController;
    }

    public void setViewClassController(ViewClassController viewClassController){
        this.viewClassController = viewClassController;
    }

    public ClassDiagram getClassDiagram(){
        return classDiagram;
    }

    public ViewDiagram getViewDiagram(){
        return viewDiagram;
    }

}
