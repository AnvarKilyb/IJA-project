package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.model.Arguments;
import ija.diagram.classdiagram.model.DiagramClass;
import ija.diagram.classdiagram.model.Item;
import ija.diagram.classdiagram.model.Methods;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewDiagram {

    private final Map<ViewClass, DiagramClass> diagramClassMap = new HashMap<ViewClass, DiagramClass>();


    public void addNewClass(DiagramClass modelClass){
        ViewClass viewClass = new ViewClass();
        String className = modelClass.getName();
        List<Arguments> argumentsList = modelClass.getArgumentsList();
        List<Methods> methodsList = modelClass.getMethodsList();
        viewClass.setClassName(className);
        addArguments(viewClass, argumentsList);
        addMethods(viewClass, methodsList);
        diagramClassMap.put(viewClass, modelClass);
        //todo x, y
    }

    private void addArguments(ViewClass viewClass, List<Arguments> argumentsList){
        for(Arguments argument : argumentsList){
            String name = argument.getName();
            String type = argument.getType();
            String accessModifierString = Item.returnString(argument.getAccessModifier());
            viewClass.addArgument(name, accessModifierString, type);
        }
    }

    private void addMethods(ViewClass viewClass, List<Methods> methodsList){
        for(Methods method : methodsList){
            String name = method.getName();
            String accessModifierString = Item.returnString(method.getAccessModifier());
            viewClass.addMethod(name, accessModifierString);
        }
    }

}
