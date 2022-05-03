package ija.diagram.classdiagram.view;

import ija.diagram.ControllerMain;
import ija.diagram.classdiagram.model.*;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Třída reprezentuje zobrazení diagramu tříd
 * obsahuje metody pro manipulace s jednotlivými třídami a vztahy
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ViewDiagram {

    /**Uchovává instance třídy a instance která reprezentuje zobrazení třídy*/
    private Map<ViewClass, DClass> diagramClassMap = new HashMap<ViewClass, DClass>();
    private ControllerMain controllerMain;

    /**Uchovává instance vztahu a instance která reprezentuje zobrazení vztahu*/
    private Map<Line, Relationships> relationshipsLineMap = new HashMap<Line, Relationships>(); //todo

    public void setControllerMain(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    /**
     * Vytvoří instance která reprezentuje zobrazení třídy a uchová ji
     * @param modelClass instance třídy
     * @return vrátí vytvořenou třídu
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
        viewClass.setY(modelClass.getY());
        //todo x, y
        return viewClass;
    }

    /**
     * Dodává zobrazení argumentu do instance zobrazení třídy
     * @param viewClass instance zobrazeni třídy
     * @param argumentsList seznam argumentů
     */
    private void addArguments(ViewClass viewClass, List<Arguments> argumentsList){
        for(Arguments argument : argumentsList){
            viewClass.addArgument(argument, controllerMain.getClassContextController());
        }
    }

    /**
     * Dodává zobrazení argumentu do instance zobrazeni třídy
     * @param viewClass instance zobrazeni třídy
     * @param argument argument
     */
    public void addArguments(ViewClass viewClass, Arguments argument){
        viewClass.addArgument(argument, controllerMain.getClassContextController());

    }

    /**
     * Dodává zobrazení metod do instance zobrazení třídy
     * @param viewClass instance zobrazeni třídy
     * @param methodsList seznam metodu
     */
    private void addMethods(ViewClass viewClass, List<Methods> methodsList){
        for(Methods method : methodsList){
            viewClass.addMethod(method, controllerMain.getClassContextController());
        }
    }

    /**
     * Dodává zobrazení argumentu do instance zobrazení třídy
     * @param viewClass instance zobrazení třídy
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

    public void deleteAll(){
        diagramClassMap.clear();
        relationshipsLineMap.clear();
    }

    public Map<ViewClass,DClass> getDiagramClassMap(){
        return diagramClassMap;
    }

    public Map<Line,Relationships> getRelationshipsLineMap(){
        return relationshipsLineMap;
    }
}
