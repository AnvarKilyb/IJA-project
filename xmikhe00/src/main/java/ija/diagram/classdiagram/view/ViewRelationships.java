package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import ija.diagram.classdiagram.controller.ViewClassController;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class ViewRelationships extends Line {

    public ViewRelationships(){
        super();
        super.setId("relation");
        super.setStartX(10.0);
        super.setStartY(10.0);
        super.setEndX(100.0);
        super.setEndY(10.0);
    }

    public void setController(ViewClassController viewClassController){
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, viewClassController::extensionLine);
    }


}
