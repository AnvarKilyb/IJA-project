package ija.diagram;
import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewDiagram;
import ija.diagram.classdiagram.view.ViewRelationships;
import ija.diagram.loader.Loader;
import ija.diagram.loader.Writer;
import ija.diagram.sequencediagram.model.SObject;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import ija.diagram.sequencediagram.view.ViewObject;
import ija.diagram.sequencediagram.view.ViewSequenceDiagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
/**
 * Hlavní ovladač,
 * kontroluje hlavní rozhraní
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ControllerMain {
    private ViewClassController viewClassController;
    private ClassContextController classContextController;
    private final SequenceDiagram sequenceDiagram;
    private final ViewSequenceDiagram viewSequenceDiagram;
    private final ClassDiagram classDiagram;
    private final ViewDiagram viewDiagram;
    private Stage stage;

    private FileChooser fileChooser;

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

    @FXML
    private Pane sequencePane;

    @FXML
    private Button buttonAddRelation;

    @FXML
    private Button buttonNewSequence;

    /**
     * Konstruktér ovladače
     * předáváme do lokálních parametrů instance tříd
     * @param classDiagram instance třídy {@link ClassDiagram}
     * @param viewDiagram instance třídy {@link ViewDiagram}
     */
    public ControllerMain(ClassDiagram classDiagram, ViewDiagram viewDiagram,
                          SequenceDiagram sequenceDiagram, ViewSequenceDiagram viewSequenceDiagram, FileChooser fileChooser, Stage stage){
            this.classDiagram = classDiagram;
            this.viewDiagram = viewDiagram;
            this.sequenceDiagram = sequenceDiagram;
            this.viewSequenceDiagram = viewSequenceDiagram;
            this.stage = stage;
            this.fileChooser = fileChooser;
    }


    /**
     * Metoda je spuštěna jen jednou při spuštění programu, pro inicializaci ovladače
     * pro elementy hlavního rozhraní (add,load)
     */
    public void initialize(){
        buttonAdd.addEventFilter(ActionEvent.ACTION,this::addClassAction);
        buttonLoad.addEventFilter(ActionEvent.ACTION,this::loadFile);
        buttonSave.addEventFilter(ActionEvent.ACTION,this::saveFile);
        buttonAddRelation.addEventHandler(ActionEvent.ACTION, this::addRelation);
        buttonNewSequence.addEventFilter(ActionEvent.ACTION, this::newSequence);
    }

    /**
     * Metoda bude spuštěna při zatlačeni tlačítka add
     * vytvoří model a view třídy a zobrazí je
     * @param event přenáší akce tlačení na tlačítko
     */
    private void addClassAction(ActionEvent event){
        DClass dClass = classDiagram.addClass();
        ViewClass viewClass = viewDiagram.addNewClass(dClass);
        this.mainPane.getChildren().add(viewClass);
    }

    private void addRelation(ActionEvent event){
        ViewRelationships relationships = new ViewRelationships();
        relationships.setController(viewClassController);
        mainPane.getChildren().add(relationships);
    }

    private void newSequence(ActionEvent event){
        SObject sObject = sequenceDiagram.addObject();
        ViewObject viewObject = viewSequenceDiagram.addNewObject(sObject);
        this.sequencePane.getChildren().add(viewObject);
    }

    private void saveFile(ActionEvent event){
        File file = fileChooser.showSaveDialog(stage);
        if(file == null){
            return;
        }

        String path = file.getAbsolutePath();

        Writer writer = new Writer(path);
        List<DClass> dClassList = classDiagram.getdClassList();
        writer.writeJSON(dClassList);
    }

    /**
     * Metoda bude spuštěna při zatlačení tlačítka load
     * spustí parser pro soubor s daty a vytvoří nové třídy a zobrazí je
     * @param event přenáší akce tlačení na tlačítko
     */
    private void loadFile(ActionEvent event){


        File file = fileChooser.showOpenDialog(this.stage);

        if(file == null){
            return;
        }

        String path = file.getAbsolutePath();

        for(ViewClass viewClass : viewDiagram.getDiagramClassMap().keySet()){
            mainPane.getChildren().remove(viewClass);
        }
        for(Line line : viewDiagram.getRelationshipsLineMap().keySet()){
            mainPane.getChildren().remove(line);
        }
        classDiagram.deleteAll();
        viewDiagram.deleteAll();


        Loader loader = new Loader(classDiagram, path);
        loader.classLoad();
        for(DClass dClass : classDiagram.getdClassList()){
            ViewClass viewClass = viewDiagram.addNewClass(dClass);
            List<Relationships> relationshipsList= classDiagram.getRelationshipsList();
            for(Relationships relationships : relationshipsList){
                Line line = viewDiagram.addRelationships(relationships);
                line.setId("relation");
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

    public SequenceDiagram getSequenceDiagram() {
        return sequenceDiagram;
    }

    public ViewSequenceDiagram getViewSequenceDiagram() {
        return viewSequenceDiagram;
    }

    public ClassDiagram getClassDiagram(){
        return classDiagram;
    }

    public ViewDiagram getViewDiagram(){
        return viewDiagram;
    }

}
