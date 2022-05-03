package ija.diagram.sequencediagram.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ViewObject extends BorderPane {
    private Label mainLabel = new Label("Object");

    public ViewObject(){
        super();
    }
    private void setVisualOptions(){
        mainLabel.setId("objectLabel");
        mainLabel.setPrefWidth(150);
        mainLabel.setPrefHeight(150);
        super.setCenter(mainLabel);
        super.setId("objectBox");
    }

    public void show(){
        this.setVisualOptions();
    }

    public void setX(double x){
        super.setLayoutX(x);
    }
    public void setY(double y){
        this.setLayoutY(y);
    }
}
