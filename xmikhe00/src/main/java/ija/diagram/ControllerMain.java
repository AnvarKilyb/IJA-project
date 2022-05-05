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
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Collections;
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
    private Label labelWarning;

    @FXML
    private Button buttonAddRelation;

    @FXML
    private  Button buttonNewSequence;
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
        relationships.setController();
        mainPane.getChildren().add(relationships);
    }

    private void newSequence(ActionEvent event){
        Pane content = new Pane();
        content.setId("sequencePane");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(880, 682);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        SObject actor = sequenceDiagram.addActor();
        ViewObject viewObject = viewSequenceDiagram.addNewObject(actor);
        content.getChildren().add(viewObject);
        if(classDiagram.getdClassList().isEmpty()){
            labelWarning.setText("Transformation warning:\n---There are no entities.");
            return;
        }
        short count = 2;
        List<DClass> randList = classDiagram.getdClassList();
        Collections.shuffle(randList);
        for(DClass dClass: randList){
            SObject sObject = new SObject();
            sObject.setName(dClass.getName());
            sObject.setX(160*count);
            count++;
            sequenceDiagram.addObject(sObject);
            ViewObject target = viewSequenceDiagram.addNewObject(sObject);
            target = viewSequenceDiagram.setActiveChunk(0, target);
            target = viewSequenceDiagram.setActiveChunk(1, target);
            target = viewSequenceDiagram.setActiveChunk(5, target);
            content.getChildren().add(target);
        }
        scrollPane.setContent(content);
        this.sequencePane.getChildren().add(scrollPane);

    }

    private void saveFile(ActionEvent event){
        File file = fileChooser.showSaveDialog(stage);
        if(file == null){
            return;
        }

        String path = file.getAbsolutePath();

        Writer writer = new Writer(path);
        writer.saveJSON(classDiagram);
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
        loader.loading();
        for(DClass dClass : classDiagram.getdClassList()){
            ViewClass viewClass = viewDiagram.addNewClass(dClass);
            this.mainPane.getChildren().add(viewClass);
        }
        List<Relationships> relationshipsList = classDiagram.getRelationshipsList();
        for(Relationships relationships : relationshipsList){
            ViewRelationships line = new ViewRelationships();
            this.mainPane.getChildren().add(line);
            line.setViewClassController(viewClassController);
            viewDiagram.addRelationships(relationships,line);
            line.setUserData(mainPane);
            line.setMainPane(mainPane);
            line.addArrow();
            line.setType(relationships.getTypeShipString());
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

    public Label getLabelWarning(){
        return labelWarning;
    }
}
