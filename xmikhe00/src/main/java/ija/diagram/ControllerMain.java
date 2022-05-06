package ija.diagram;
import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.NameRepeatedController;
import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewDiagram;
import ija.diagram.classdiagram.view.ViewRelationships;
import ija.diagram.loader.Loader;
import ija.diagram.loader.Writer;
import ija.diagram.sequencediagram.controller.AddObjectController;
import ija.diagram.sequencediagram.controller.ContextObjectController;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import ija.diagram.sequencediagram.view.ViewSequenceDiagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    private ContextObjectController contextObjectController;

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
    private Label labelWarning;

    @FXML
    private RadioButton choice1;

    @FXML
    private RadioButton choice2;

    @FXML
    private RadioButton choice3;

    @FXML
    private Pane sequencePane1;

    @FXML
    private Pane sequencePane2;

    @FXML
    private Pane sequencePane3;

    @FXML
    private Button buttonAddObject;


    ToggleGroup sequenceGroup = new ToggleGroup();

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
        buttonAddObject.addEventFilter(ActionEvent.ACTION, this::addObject);
        choice1.setToggleGroup(sequenceGroup);
        choice2.setToggleGroup(sequenceGroup);
        choice3.setToggleGroup(sequenceGroup);
        choice1.fire();
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


    private void saveFile(ActionEvent event){

        if(classDiagram.checkAllClassName()){
            FXMLLoader loader = new FXMLLoader(ControllerMain.class.getResource("/main/nameRepeat.fxml"));
            NameRepeatedController nameRepeatedController = new NameRepeatedController();
            nameRepeatedController.setControllerMain(this);
            loader.setController(nameRepeatedController);
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage repeatWindow = new Stage();
            repeatWindow.setScene(scene);
            repeatWindow.initModality(Modality.APPLICATION_MODAL);
            repeatWindow.setResizable(false);
            repeatWindow.showAndWait();

            labelWarning.setPrefHeight(labelWarning.getHeight() + 15);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            labelWarning.setText(labelWarning.getText() + "\n[" + dateFormat.format(date) + "] Please save again ");
            return;
        }

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
        classDiagram.setAllReaped();
        List<DClass> dClassListCopy = new ArrayList<>(classDiagram.getdClassList());
        for(DClass dClass : dClassListCopy){
            if(dClass.getReapedName()){
                String text = "class named " + dClass.getName() + "\nremoved due to name repetition \nand its relation";
                writeLabelWarning(text);
                continue;
            }
            ViewClass viewClass = viewDiagram.addNewClass(dClass);
            this.mainPane.getChildren().add(viewClass);
        }
        List<Relationships> relationshipsList = classDiagram.getRelationshipsList();
        List<Relationships> relationshipsListCopy =  new ArrayList<>(relationshipsList);
        for(Relationships relationships : relationshipsListCopy){
            ViewRelationships line = new ViewRelationships();
            this.mainPane.getChildren().add(line);
            line.setViewClassController(viewClassController);
            if(viewDiagram.addRelationships(relationships,line) == null){
                this.mainPane.getChildren().remove(line);
                classDiagram.deleteRelation(relationships);
                continue;
            }
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

    public void addObject(ActionEvent actionEvent){
        if(classDiagram.getdClassList().isEmpty()){
            writeLabelWarning("Transformation warning:\n---There are no entities.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(ControllerMain.class.getResource("/main/objectNewWindow.fxml"));
        AddObjectController addObjectController = new AddObjectController();
        addObjectController.setControllerMain(this);
        loader.setController(addObjectController);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage addObjectWindow = new Stage();
        addObjectWindow.setScene(scene);
        addObjectWindow.initModality(Modality.APPLICATION_MODAL);
        addObjectWindow.setResizable(false);
        addObjectWindow.showAndWait();
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


    public Pane getSequencePane(){
        RadioButton selectedRadioButton = (RadioButton) sequenceGroup.getSelectedToggle();
        switch (selectedRadioButton.getText()){
            case "1":
                return sequencePane1;
            case "2":
                return sequencePane2;
            case "3":
                return sequencePane3;
        }
        return sequencePane1;
    }


    public Pane getMainPane(){
        return mainPane;
    }

    public void setContextObjectController(ContextObjectController contextObjectController){
        this.contextObjectController = contextObjectController;
    }

    public ContextObjectController getContextObjectController(){
        return this.contextObjectController;
    }

    public void writeLabelWarning(String text){
        labelWarning.setPrefHeight(labelWarning.getHeight() + 30);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        labelWarning.setText(labelWarning.getText() + "\n[" + dateFormat.format(date) + "] " + text);
    }
}
