package ija.diagram.sequencediagram.view;

import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.sequencediagram.model.Message;
import ija.diagram.sequencediagram.model.SObject;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.Map;

public class ViewSequenceDiagram {

    private ControllerMain controllerMain;

    private Map<Line, Message> messageLineMap = new HashMap<Line, Message>();

    private  Map<ViewObject, SObject> objectSequenceMap = new HashMap<ViewObject, SObject>();

    public void setControllerMain(ControllerMain controllerMain){this.controllerMain = controllerMain;}


    public ViewObject addNewObject(SObject modelObject){
        ViewObject viewObject = new ViewObject();
        viewObject.setObjectName(modelObject.getName());
        viewObject.show();
        objectSequenceMap.put(viewObject, modelObject);
        viewObject.setX(modelObject.getX());
        viewObject.setY(modelObject.getY());
        return viewObject;
    }


    public Map<Line, Message> getMessageLineMap() {
        return messageLineMap;
    }

    public Map<ViewObject, SObject> getObjectSequenceMap() {
        return objectSequenceMap;
    }
}