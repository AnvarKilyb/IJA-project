package ija.diagram.sequencediagram.model;

import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Item;
import ija.diagram.classdiagram.model.Methods;
import ija.diagram.classdiagram.model.Relationships;

import java.util.ArrayList;
import java.util.List;

public class SequenceDiagram {
    private List<SObject> sObjectList = new ArrayList<>();
    private List<RelatedActivationBoxes> relatedActivationBoxesList = new ArrayList<>();
    private final int indent = 130;


    public SObject addObject(DClass dClass){
        SObject sObject =  getObject(dClass);
        if(sObject != null){
            return null;
        }
        sObject = new SObject();
        sObject.setName(dClass.getName());
        sObject.setThisClass(dClass);
        sObject.setX(sObjectList.size() * indent);
        sObjectList.add(sObject);
        return sObject;
    }

    private SObject getObject(DClass dClass){
        for(SObject sObject : sObjectList){
            if (sObject.getName().equals(dClass.getName())){
                return sObject;
            }
        }
        return null;
    }

    public SObject getObject(String name){
        for(SObject sObject: sObjectList){
            if(sObject.getName().equals(name)){
                return sObject;
            }
        }
        return null;
    }
}
