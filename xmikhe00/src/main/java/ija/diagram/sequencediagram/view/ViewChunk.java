package ija.diagram.sequencediagram.view;

import javafx.scene.layout.Pane;

public class ViewChunk extends Pane {

    public ViewChunk(){
        //super.setId("chunkTransparent");
        super();
        super.setId("chunkTransparent");
    }

    public void activeChunk(){
        super.setId("chunkVisible");
    }
    public void setX(double x){
        super.setLayoutX(x);
    }
    public void setY(double y){
        super.setLayoutY(y);
    }
}
