package ija.diagram.loader;

import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.sequencediagram.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída převádí do modelu dat již analyzované soubory
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Loader {
    private final ClassDiagram classDiagram;
    private short sequenceCounter = 0;
    private final SequenceDiagram sequenceDiagram1;
    private final SequenceDiagram sequenceDiagram2;
    private final SequenceDiagram sequenceDiagram3;
    private ArrayList<objectJSON> list;
    private Parser parser;


    public void loading(){
        list = parser.parseJSON();
        if(list == null){
            return;
        }
        for(objectJSON item: list){
            if(item.getType() == objectJSON.ItemType.CLASS){
                DClass dClass  = parseClasses(item);
                classDiagram.addClass(dClass);

            }
            if(item.getType() == objectJSON.ItemType.SEQUENCE){
                if(sequenceCounter == 0)
                    parseSequence(item, sequenceDiagram1);
                else if(sequenceCounter == 1)
                    parseSequence(item, sequenceDiagram2);
                else if(sequenceCounter == 2)
                    parseSequence(item, sequenceDiagram3);
                sequenceCounter++;
            }
        }
        parseRelation();
    }

    public Loader(ClassDiagram classDiagram,SequenceDiagram sequenceDiagram1,SequenceDiagram sequenceDiagram2,SequenceDiagram sequenceDiagram3, String path){
        this.classDiagram = classDiagram;
        this.parser = new Parser(path);
        this.sequenceDiagram1 = sequenceDiagram1;
        this.sequenceDiagram2 = sequenceDiagram2;
        this.sequenceDiagram3 = sequenceDiagram3;
    }
    private void parseSequence(objectJSON item, SequenceDiagram sequenceDiagram){
        boolean fraud;
        for(String participant: item.getParticipantList()){
            fraud = true;
            for(DClass dClass: classDiagram.getdClassList()){
                if(participant.equals(dClass.getName())){
                    sequenceDiagram.addObject(dClass);
                    fraud = false;
                }
            }
            if(fraud){
                //TODO
            }
        }
        for(SObject sObject: sequenceDiagram.getsObjectList()){
            ActivationBox activationBox = new ActivationBox();
            for(Message message: item.getMessageList()){
                if(message.getClassStart().getName().equals(sObject.getName())){
                    activationBox.setThisObject(sObject);
                    activationBox.addNewOutMessage(message.getName(), message.getMessageType());
                }
                if(message.getClassEnd().getName().equals(sObject.getName())){
                    activationBox.setThisObject(sObject);
                    activationBox.addNewInMessage(message);
                }
            }
            sObject.addActiveBox(activationBox);
        }

    }


    private void parseRelation() {
        //Parse relations
        for (objectJSON relation : list) {
            if (relation.getType() == objectJSON.ItemType.CONNECTION) {
                double sX = relation.getStartX();
                double sY = relation.getStartY();
                double eX = relation.getEndX();
                double eY = relation.getEndY();
                DClass classFrom = classDiagram.returnClass(relation.getStartConnection());
                DClass classTo = classDiagram.returnClass(relation.getEndConnection());
                switch (relation.getConType()) {
                    case ASSOCIATION:
                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.ASSOCIATION, sX, sY, eX, eY);
                        break;
                    case AGGREGATION:
                        classDiagram.addRelationship(classFrom,classTo,  Relationships.Type.AGGREGATION, sX, sY, eX, eY);
                        break;
//                    case REFLEXIVE_ASSOCIATION:
//                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.REFLEXIVE, sX, sY, eX, eY);
//                        break;
//                    case MULTIPLICITY:
//                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.MULTIPLICITY, sX, sY, eX, eY);
//                        break;
                    case COMPOSITION:
                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.COMPOSITION, sX, sY, eX, eY);
                        break;
                    case INHERITANCE:
                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.INHERITANCE_GENERALIZATION, sX, sY, eX, eY);
                        break;
                    case REALIZATION:
                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.REALIZATION, sX, sY, eX, eY);
                        break;
                    default:
                        classDiagram.addRelationship(classFrom,classTo, Relationships.Type.ASSOCIATION, sX, sY, eX, eY);
                        break;

                }
            }
        }
    }

    private DClass parseClasses(objectJSON item){
        DClass dClass = new DClass(item.getName());
        if(item.getAttributes() != null){
            for(attrJSON attr: item.getAttributes()) {
                dClass.addArgument(attr.getName(), attr.getAccessModifiers(), attr.getType());
            }
        }
        if(item.getOperations() != null){
            for(attrJSON method: item.getOperations()){
                dClass.addMethod(method.getName(), method.getAccessModifiers());
            }
        }
        dClass.setX(item.getxAxis());
        dClass.setY(item.getyAxis());
        return dClass;
    }

}
