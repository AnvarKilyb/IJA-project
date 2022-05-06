package ija.diagram.classdiagram.Actions;

import ija.diagram.classdiagram.controller.ViewClassController;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.classdiagram.view.ViewDiagram;
import ija.diagram.classdiagram.view.ViewRelationships;
import ija.diagram.loader.Loader;
import ija.diagram.sequencediagram.model.SequenceDiagram;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadFileAction implements Action{
    private FileChooser fileChooser;
    private Stage stage;
    private ViewDiagram viewDiagram;
    private Pane mainPane;
    private ClassDiagram classDiagram;
    private SequenceDiagram sequenceDiagram1;
    private SequenceDiagram sequenceDiagram2;
    private SequenceDiagram sequenceDiagram3;
    private Label labelWarning;
    private ViewClassController viewClassController;

    public LoadFileAction(FileChooser fileChooser, Stage stage, ViewDiagram viewDiagram, Pane mainPane, ClassDiagram classDiagram,
                          SequenceDiagram sequenceDiagram1, SequenceDiagram sequenceDiagram2, SequenceDiagram sequenceDiagram3,
                          Label labelWarning, ViewClassController viewClassController) {
        this.fileChooser = fileChooser;
        this.stage = stage;
        this.viewDiagram = viewDiagram;
        this.mainPane = mainPane;
        this.classDiagram = classDiagram;
        this.sequenceDiagram1 = sequenceDiagram1;
        this.sequenceDiagram2 = sequenceDiagram2;
        this.sequenceDiagram3 = sequenceDiagram3;
        this.labelWarning = labelWarning;
        this.viewClassController = viewClassController;
    }


    @Override
    public void run() {
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


        Loader loader = new Loader(classDiagram, sequenceDiagram1,sequenceDiagram2,sequenceDiagram3, path);
        loader.loading();
        classDiagram.setAllReaped();
        List<DClass> dClassListCopy = new ArrayList<>(classDiagram.getdClassList());
        for(DClass dClass : dClassListCopy){
            if(dClass.getReapedName()){
                labelWarning.setPrefHeight(labelWarning.getHeight() + 30);
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                labelWarning.setText(labelWarning.getText() + "\n[" + dateFormat.format(date) + "] class named " + dClass.getName() + "\nremoved due to name repetition \nand its relation ");
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

    @Override
    public void undo() {
        for(ViewClass viewClass: viewDiagram.getDiagramClassMap().keySet()){
            mainPane.getChildren().remove(viewClass);
        }
        for(ViewRelationships viewRelationships: viewDiagram.getRelationshipsLineMap().keySet()){
            mainPane.getChildren().remove(viewRelationships);
        }
    }
}
