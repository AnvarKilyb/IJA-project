package ija.diagram.sequencediagram.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ViewObject extends BorderPane {
    private Label mainLabel = new Label("Object");
    private Line lifeLine = new Line();

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
        super.setCenter(lifeLine);
        super.setTop(mainLabel);
        //super.setId("objectBox");
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

    public void setObjectName(String objectName) {
        this.mainLabel.setText(objectName);
    }
}
