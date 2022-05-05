package ija.diagram.loader;

import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;

import java.util.ArrayList;

/**
 * Třída převádí do modelu dat již analyzované soubory
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Loader {
    private final ClassDiagram classDiagram;
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
        }
        parseRelation();
    }

    public Loader(ClassDiagram classDiagram, String path){
        this.classDiagram = classDiagram;
        this.parser = new Parser(path);
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
