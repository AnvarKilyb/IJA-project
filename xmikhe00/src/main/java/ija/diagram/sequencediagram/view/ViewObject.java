package ija.diagram.sequencediagram.view;

import ija.diagram.sequencediagram.model.ActivationBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class ViewObject extends Pane {
    private Label mainLabel = new Label("Object");
    private Line lifeLine = new Line();
    private ViewActiveBox viewActiveBox = null;

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
//        for(short start = 100; start <= 600; start += 25){
//            ViewChunk chunk = new ViewChunk();
//            chunk.setPrefHeight(25);
//            chunk.setPrefWidth(20);
//            chunk.setX(30);
//            chunk.setY(start);
//            super.getChildren().add(chunk);
//            chunkList.add(chunk);
//        }
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

    public ViewActiveBox getViewActiveBox(){
        return this.viewActiveBox;
    }

    public ViewActiveBox addViewActionBox(ActivationBox activationBox){
        viewActiveBox = new ViewActiveBox();
        viewActiveBox.setY(activationBox.getY());
        viewActiveBox.setX(activationBox.getX());
        viewActiveBox.setPrefHeight(activationBox.getHeight());
        return  viewActiveBox;
    }
}
