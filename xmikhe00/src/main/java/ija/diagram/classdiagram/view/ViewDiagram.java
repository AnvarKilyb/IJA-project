package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ControllerMain;
import ija.diagram.classdiagram.model.Arguments;
import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Methods;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewDiagram {

    private Map<ViewClass, DClass> diagramClassMap = new HashMap<ViewClass, DClass>();
    private ControllerMain controllerMain;

    public void setControllerMain(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

//    public
    public ViewClass addNewClass(DClass modelClass){
        ViewClass viewClass = new ViewClass();
        viewClass.show(controllerMain.getClassContextController(), controllerMain.getViewClassController());
        String className = modelClass.getName();
        List<Arguments> argumentsList = modelClass.getArgumentsList();
        List<Methods> methodsList = modelClass.getMethodsList();
        viewClass.setClassName(className);
        addArguments(viewClass, argumentsList);
        addMethods(viewClass, methodsList);
        diagramClassMap.put(viewClass, modelClass);
        viewClass.setX(modelClass.getX());
        viewClass.setY(modelClass.setY());
        //todo x, y
        return viewClass;
    }


    private void addArguments(ViewClass viewClass, List<Arguments> argumentsList){
        for(Arguments argument : argumentsList){
            viewClass.addArgument(argument, controllerMain.getClassContextController());
        }
    }

    public void addArguments(ViewClass viewClass, Arguments argument){
        viewClass.addArgument(argument, controllerMain.getClassContextController());

    }

    private void addMethods(ViewClass viewClass, List<Methods> methodsList){
        for(Methods method : methodsList){
            viewClass.addMethod(method, controllerMain.getClassContextController());
        }
    }

    public void addMethods(ViewClass viewClass, Methods method){
        viewClass.addMethod(method, controllerMain.getClassContextController());
    }

    public DClass returnDClass(ViewClass viewClass){
        return diagramClassMap.get(viewClass);
    }

    public void deleteClass(ViewClass viewClass){
       diagramClassMap.remove(viewClass);
    }
}
