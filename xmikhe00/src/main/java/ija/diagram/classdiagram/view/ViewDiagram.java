package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ControllerMain;
import ija.diagram.classdiagram.model.*;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Trida reprezentuje zobrazeni diagramy trid
 * obsahuje metody pro manipulave s jednotlivami tridami a vzthami
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ViewDiagram {
    /**
     * Uchovova instance tridy a instance ktera reprezentuje zobrazeni tridy
     */
    private Map<ViewClass, DClass> diagramClassMap = new HashMap<ViewClass, DClass>();
    private ControllerMain controllerMain;

    public void setControllerMain(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }
    /**
     * Uchovova instance vztahu a instance ktera reprezentuje zobrazeni vztahu
     */
    private Map<Line, Relationships> relationshipsLineMap = new HashMap<Line, Relationships>(); //todo

    /**
     * Vytvori instance ktera reprezentuje zobrazeni tridy a uchova ji
     * @param modelClass instance tridy
     * @return vrati vytvorenou tridu
     */
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

    /**
     * Dodova zobraeni argumentu do instance zobrazeni tridy
     * @param viewClass instance zobrazeni tridy
     * @param argumentsList seznma argumentu
     */
    private void addArguments(ViewClass viewClass, List<Arguments> argumentsList){
        for(Arguments argument : argumentsList){
            viewClass.addArgument(argument, controllerMain.getClassContextController());
        }
    }

    /**
     * Dodova zobraeni argumentu do instance zobrazeni tridy
     * @param viewClass instance zobrazeni tridy
     * @param argument argument
     */
    public void addArguments(ViewClass viewClass, Arguments argument){
        viewClass.addArgument(argument, controllerMain.getClassContextController());

    }

    /**
     * Dodova zobrazeni metodu do instance zobrazeni tridy
     * @param viewClass instance zobrazeni tridy
     * @param methodsList seznma metodu
     */
    private void addMethods(ViewClass viewClass, List<Methods> methodsList){
        for(Methods method : methodsList){
            viewClass.addMethod(method, controllerMain.getClassContextController());
        }
    }

    /**
     * Dodova zobraeni argumentu do instance zobrazeni tridy
     * @param viewClass instance zobrazeni tridy
     * @param method metod
     */
    public void addMethods(ViewClass viewClass, Methods method){
        viewClass.addMethod(method, controllerMain.getClassContextController());
    }

    public DClass returnDClass(ViewClass viewClass){
        return diagramClassMap.get(viewClass);
    }

    public void deleteClass(ViewClass viewClass){
       diagramClassMap.remove(viewClass);
    }


    public Line addRelationships(Relationships relationships){
        Line line = new Line();
        line.setStartX(relationships.getStartX());
        line.setStartY(relationships.getStartY());
        line.setEndX(relationships.getEndX());
        line.setEndY(relationships.getEndY());
        relationshipsLineMap.put(line, relationships);
        return line;
    }
}
