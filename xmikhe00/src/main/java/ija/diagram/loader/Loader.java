package ija.diagram.loader;

import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Item;
import ija.diagram.classdiagram.model.Relationships;

import java.util.ArrayList;

import static ija.diagram.loader.parser.parseJSON;

public class Loader {
    private ClassDiagram classDiagram;

    public void classLoad(){
        ArrayList<objectJSON> list = parseJSON();
        for(objectJSON item: list){
            if(item.getType() == objectJSON.ItemType.CLASS){
                classDiagram.addClass(parseDiagram(item));
            }
        }
    }

    public Loader(ClassDiagram classDiagram){
        this.classDiagram = classDiagram;
    }

    /**
     * Method parseDiagram()
     *      Receives objectJson object from parsed list.
     *      Creates DiagramCLass object, fills its parameters(argumentsList,methodsList,relationshipsList,xAxis,yAxis).
     *      Returns specified object.
     */
    private DClass parseDiagram(objectJSON item) {
        ArrayList<objectJSON> list = parseJSON();
        DClass dClass  = parseClasses(item);
        //Parse relations
        for (objectJSON relation : list) {
            if (relation.getType() == objectJSON.ItemType.CONNECTION) {
                if (relation.getStartConnection().equals(item.getName())) {
                    switch (relation.getConType()) {
                        case ASSOCIATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.ASSOCIATION);
                            break;
                        case AGGREGATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.AGGREGATION);
                            break;
                        case REFLEXIVE_ASSOCIATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.REFLEXIVEASSOCIATION, Relationships.Type.REFLEXIVEASSOCIATION);
                            break;
                        case MULTIPLICITY:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.MULTIPLICITY);
                            break;
                        case COMPOSITION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.COMPOSITION);
                            break;
                        case INHERITANCE:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.INHERITANCE_GENERALIZATION);
                            break;
                        case REALIZATION:
                            dClass.addRelationship(relation.getEndConnection(), Relationships.Type.ASSOCIATION, Relationships.Type.REALIZATION);
                            break;
                    }
                }
            }
        }
        return dClass;
    }
    /**
     * Method parseClasses()
     *      Aux method of parseDiagram(objectJSON).
     *      Determine attributes and methods of class.
     *
     */
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
