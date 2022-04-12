package ija.diagram.classdiagram.controller;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewDiagram;
import ija.diagram.loader.Loader;
import ija.diagram.loader.Writer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.List;
/**
 * Hlavní ovladač,
 * kontroluje hlavni rozhraní
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ControllerMain {
    private ViewClassController viewClassController;
    private ClassContextController classContextController;
    private final ClassDiagram classDiagram;
    private final ViewDiagram viewDiagram;

    /**
     * Konstruktér ovladače
     * předáváme do lokálních parametru instance tříd
     * @param classDiagram instance třídy {@link ClassDiagram}
     * @param viewDiagram instance třídy {@link ViewDiagram}
     */
    public ControllerMain(ClassDiagram classDiagram, ViewDiagram viewDiagram){
            this.classDiagram = classDiagram;
            this.viewDiagram = viewDiagram;
    }

    /**
     * Obsahuje instance třídy tlačítka pro dodávaná třídy
     */
    @FXML
    private Button buttonAdd;

    /**
     * Obsahuje instance třídy tlačítka pro načtení souborů
     */
    @FXML
    private Button buttonLoad;

    /**
     * Obsahuje instance třídy tlačítka pro uložení souborů
     */
    @FXML
    private Button buttonSave;

    /**
     * Obsahuje instance třídy hlavního pole
     */
    @FXML
    private Pane mainPane;

    /**
     * Method spuštěn jen jednou pri spuštění programu, pro inicializace ovladače
     * pro elementy hlavního rozhraní (add,load)
     */
    public void initialize(){
        buttonAdd.addEventFilter(ActionEvent.ACTION,this::addClassAction);
        buttonLoad.addEventFilter(ActionEvent.ACTION,this::loadFile);
        buttonSave.addEventFilter(ActionEvent.ACTION,this::saveFile);
    }

    /**
     * Metod bude spušten pri ztlaceni tlacitka add
     * vytvorit model a view třídy a zobrazí je
     * @param event prenaší akce tlaceni na tlacitko
     */
    private void addClassAction(ActionEvent event){
        DClass dClass = classDiagram.addClass();
        ViewClass viewClass = viewDiagram.addNewClass(dClass);
        this.mainPane.getChildren().add(viewClass);
    }

    private void saveFile(ActionEvent event){
        Writer writer = new Writer();
        List<DClass> dClassList = classDiagram.getdClassList();
        writer.writeJSON(dClassList);
    }

    /**
     * Metod bude spušten pri ztlaceni tlacitka load
     * spustí parser souboru s data a vytvori nove tridy a zobrazi
     * @param event prenaší akce tlaceni na tlacitko
     */
    private void loadFile(ActionEvent event){
        //todo delete old diagram
        Loader loader = new Loader(classDiagram);
        loader.classLoad();
        for(DClass dClass : classDiagram.getdClassList()){
            ViewClass viewClass = viewDiagram.addNewClass(dClass);
            List<Relationships> relationshipsList= dClass.getRelationshipsList();
            for(Relationships relationships : relationshipsList){
                Line line = viewDiagram.addRelationships(relationships);
                this.mainPane.getChildren().add(line);
            }
            this.mainPane.getChildren().add(viewClass);
//            System.out.println(viewClass.getLayoutX());
        }

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
