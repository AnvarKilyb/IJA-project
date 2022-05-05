package ija.diagram.sequencediagram.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewObject extends Pane {
    private Label mainLabel = new Label("Object");
    private Line lifeLine = new Line();
    private List<ViewChunk> chunkList = new ArrayList<>();

    public ViewObject(){
        super();
    }
    private void setVisualOptions(){
        mainLabel.setId("objectLabel");
        lifeLine.setId("lifeLine");
        lifeLine.setStartX(40);
        lifeLine.setStartY(80);
        lifeLine.setEndX(40);
        lifeLine.setEndY(600);
        mainLabel.setPrefWidth(80);
        mainLabel.setPrefHeight(80);
        super.setPrefHeight(600);
        super.setPrefWidth(80);
        for(short start = 100; start <= 600; start += 25){
            ViewChunk chunk = new ViewChunk();
            chunk.setPrefHeight(25);
            chunk.setPrefWidth(20);
            chunk.setX(30);
            chunk.setY(start);
            super.getChildren().add(chunk);
            chunkList.add(chunk);
        }
        super.getChildren().add(lifeLine);
        super.getChildren().add(mainLabel);
    }

    public void show(){
        this.setVisualOptions();
    }

    public void setX(double x){
        super.setLayoutX(x);
    }
    public void setY(double y){
        super.setLayoutY(y);
    }
    public double getX(){
        return super.getLayoutX();
    }
    public double getY(){
        return super.getLayoutY();
    }

    public void setObjectName(String objectName) {
        this.mainLabel.setText(objectName);
    }

    public List<ViewChunk> getChunkList() {
        return chunkList;
    }

    public void setChunkList(List<ViewChunk> chunkList) {
        this.chunkList = chunkList;
    }
}
