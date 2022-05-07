package ija.diagram.sequencediagram.view;

import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.view.ViewClass;
import ija.diagram.sequencediagram.model.Message;
import ija.diagram.sequencediagram.model.SObject;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ViewSequenceDiagram {

    private ControllerMain controllerMain;

    private  Map<ViewObject, SObject> objectSequenceMap = new HashMap<ViewObject, SObject>();


    public void setControllerMain(ControllerMain controllerMain){this.controllerMain = controllerMain;}


    public ViewObject addNewObject(SObject modelObject){
        ViewObject viewObject = new ViewObject();
        viewObject.setObjectName(modelObject.getName());
        viewObject.show();
        objectSequenceMap.put(viewObject, modelObject);
        viewObject.setLayoutX(modelObject.getX());
        viewObject.setLayoutY(modelObject.getY());
        return viewObject;
    }

    public SObject returnObject(ViewObject viewObject){
        return objectSequenceMap.get(viewObject);
    }


    public ViewObject getViewObject(SObject sObject){
        Set<Map.Entry<ViewObject,SObject>> viewClassCollation = objectSequenceMap.entrySet();
        for(Map.Entry<ViewObject, SObject> map: viewClassCollation){
            if(map != null){
                if(sObject.equals(map.getValue())){
                    return map.getKey();
                }
            }
        }
        return null;
    }

    public void deleteObject(ViewObject viewObject){
        objectSequenceMap.remove(viewObject);
    }

    public void deleteAll(){
        objectSequenceMap.clear();
    }

    public Map<ViewObject, SObject> getObjectSequenceMap() {
        return objectSequenceMap;
    }
}
