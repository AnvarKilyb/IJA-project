package ija.diagram.loader;

import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Relationships;
import ija.diagram.loader.Parser;

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
    private final Parser parser = new Parser();

    public void classLoad(){
        list = parser.parseJSON();
        if(list == null){
            return;
        }
        for(objectJSON item: list){
            if(item.getType() == objectJSON.ItemType.CLASS){
                classDiagram.addClass(parseDiagram(item));
            }
        }
    }

    public Loader(ClassDiagram classDiagram){
        this.classDiagram = classDiagram;
    }


    private DClass parseDiagram(objectJSON item) {
//        ArrayList<objectJSON> list = parser.parseJSON();
        DClass dClass  = parseClasses(item);
        //Parse relations
        for (objectJSON relation : list) {
            if (relation.getType() == objectJSON.ItemType.CONNECTION) {
                if (relation.getStartConnection().equals(item.getName())) {
                    double sX = relation.getStartX();
                    double sY = relation.getStartY();
                    double eX = relation.getEndX();
                    double eY = relation.getEndY();
                    switch (relation.getConType()) {
                        case ASSOCIATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.ASSOCIATION, sX, sY, eX, eY);
                            break;
                        case AGGREGATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.AGGREGATION, sX, sY, eX, eY);
                            break;
                        case REFLEXIVE_ASSOCIATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.REFLEXIVEASSOCIATION, Relationships.Type.REFLEXIVEASSOCIATION, sX, sY, eX, eY);
                            break;
                        case MULTIPLICITY:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.MULTIPLICITY, sX, sY, eX, eY);
                            break;
                        case COMPOSITION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.COMPOSITION, sX, sY, eX, eY);
                            break;
                        case INHERITANCE:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.INHERITANCE_GENERALIZATION, sX, sY, eX, eY);
                            break;
                        case REALIZATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.REALIZATION, sX, sY, eX, eY);
                            break;
                    }
                }
            }
        }
        return dClass;
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
