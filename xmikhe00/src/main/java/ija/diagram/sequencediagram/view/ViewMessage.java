package ija.diagram.sequencediagram.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ViewMessage extends AnchorPane {

    private Pane line;
    private Pane arrow;
    private Label text;

    public ViewMessage(){
        super();
        super.prefHeight(16);
        super.prefWidth(104);
    }


    public void setElement(){
        line = new Pane();
        line.setPrefHeight(2);
        line.setPrefWidth(104);
        line.setId("chunkVisible");
        AnchorPane.setTopAnchor(line,5.0);
        AnchorPane.setLeftAnchor(line, 0.0);
        AnchorPane.setRightAnchor(line, 0.0);

        arrow = new Pane();
        arrow.setPrefHeight(12);
        arrow.setPrefWidth(12);
        AnchorPane.setTopAnchor(arrow,0.0);
        AnchorPane.setRightAnchor(arrow, 0.0);
        arrow.setId("arrowComposition");

        text = new Label();
        AnchorPane.setTopAnchor(text,-10.0);
        AnchorPane.setBottomAnchor(text,7.0);
//        AnchorPane.setRightAnchor(text, 100.0);
        text.setText("exampl");

        this.getChildren().addAll(text,line,arrow);

    }

    public void setText(String name){
        this.text.setText(name);
    }




}
