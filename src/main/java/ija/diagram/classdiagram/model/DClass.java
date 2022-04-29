package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezentuje třídu v diagramu tříd
 * obsahuje metody pro vytvaření a odstranění metod a argumentů
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class DClass extends Element{
    private List<Arguments> argumentsList;
    private List<Methods> methodsList;
    private List<Relationships> relationshipsList;
    private double X = 0;
    private double Y = 0;
    //todo coordinates

    public DClass(String name){
        super(name);
        argumentsList = new ArrayList<Arguments>();
        methodsList = new ArrayList<Methods>();
        relationshipsList = new ArrayList<Relationships>();
    }

    public List<Arguments> getArgumentsList(){
        return argumentsList;
    }

    public List<Methods> getMethodsList(){
        return methodsList;
    }

    public List<Relationships> getRelationshipsList(){
        return relationshipsList;
    }

    /**
     * Vytvoří argument a uloží ho do seznamu argumentů
     * @param name jméno argumentu
     * @param accessModifier typ přístupového modifikátoru
     * @param type typ argumentu
     */
    public Arguments addArgument(String name, Item.AccessModifier accessModifier,String type ){
        Arguments newArg = new Arguments(name,accessModifier);
        newArg.setType(type);
        argumentsList.add(newArg);
        return newArg;
    }

    /**
     * Vytvoří a uloží prázdný argument do seznamu argumentu
     */
    public Arguments addArgument(){
        Arguments newArg = new Arguments();
        argumentsList.add(newArg);
        return newArg;
    }

    /**
     * Odstraní argument
     * @param argument instance argumentu
     */
    public void argumentDelete(Arguments argument){
        this.argumentsList.remove(argument);
    }

    /**
     * Vytvoří metodu a uloží ji do seznamu metod
     * @param name jméno metodu
     * @param accessModifier typ přístupového modifikátoru
     */
    public Methods addMethod(String name, Item.AccessModifier accessModifier){
        Methods methods = new Methods(name,accessModifier);
        methodsList.add(methods);
        return methods;
    }

    /**
     * Vytvoří a uloží prázdnou metodu do seznamu argumentů
     */
    public Methods addMethod(){
        Methods methods = new Methods();
        methodsList.add(methods);
        return methods;
    }

    /**
     * Odstraní metodu
     * @param method instance metod
     */
    public void methodDelete(Methods method){
        this.methodsList.remove(method);
    }

    /**
     * @deprecated bude změněno a z třídy reprezentace tříd přesunuto do třídy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo){
        Relationships relationships = new Relationships(nameClassTo,typeFrom,typeTo);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude změněno a z třídy reprezentace tříd přesunuto do třídy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo,
                                         double startX, double startY, double endX, double endY){
        Relationships relationships = new Relationships(nameClassTo,typeFrom,typeTo,startX,startY,endX,endY);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude změněno a z třídy reprezentace tříd přesunuto do třídy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo){
        Relationships relationships = new Relationships(nameClassTo);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude změněno a z třídy reprezentace tříd přesunuto do třídy {@link ClassDiagram}
     */
    public void relationshipDelete(Relationships relationships){
        this.relationshipsList.remove(relationships);
    }

    /**
     * @deprecated bude změněno a z třídy reprezentace tříd přesunuto do třídy {@link ClassDiagram}
     */
    public List<Relationships> returnRelationShipList(){
        return relationshipsList;
    }


    public void setX(double x){
        this.X = x;
    }

    public double getX(){
        return this.X;
    }

    public void setY(double y){
        this.Y = y;
    }

    public double getY(){
        return this.Y;
    }


}
