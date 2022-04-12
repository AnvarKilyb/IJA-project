package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezentuje tridu v diagrame trid
 * obsahuje metody pro vytvoření a odstranění metod a argumentů
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
     * Vytvari argument a ulozi ho do seznamu argumentu
     * @param name jmeno argumentu
     * @param accessModifier typ přístupového modifikátoru
     * @param type typargumentu
     */
    public Arguments addArgument(String name, Item.AccessModifier accessModifier,String type ){
        Arguments newArg = new Arguments(name,accessModifier);
        newArg.setType(type);
        argumentsList.add(newArg);
        return newArg;
    }

    /**
     * Vytvari a ulozi prazny argument ho do seznamu argumentu
     */
    public Arguments addArgument(){
        Arguments newArg = new Arguments();
        argumentsList.add(newArg);
        return newArg;
    }

    /**
     * Odstrani argument
     * @param argument instance argumentu
     */
    public void argumentDelete(Arguments argument){
        this.argumentsList.remove(argument);
    }

    /**
     * Vytvari metod a ulozi ho do seznamu metodu
     * @param name jmeno metodu
     * @param accessModifier typ přístupového modifikátoru
     */
    public Methods addMethod(String name, Item.AccessModifier accessModifier){
        Methods methods = new Methods(name,accessModifier);
        methodsList.add(methods);
        return methods;
    }

    /**
     * Vytvari a ulozi prazny metod ho do seznamu argumentu
     */
    public Methods addMethod(){
        Methods methods = new Methods();
        methodsList.add(methods);
        return methods;
    }

    /**
     * Odstrani metod
     * @param method instance metodu
     */
    public void methodDelete(Methods method){
        this.methodsList.remove(method);
    }

    /**
     * @deprecated bude zmeneno a z tridy reprezentace trid presunuto do tridy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo){
        Relationships relationships = new Relationships(nameClassTo,typeFrom,typeTo);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude zmeneno a z tridy reprezentace trid presunuto do tridy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo,
                                         double startX, double startY, double endX, double endY){
        Relationships relationships = new Relationships(nameClassTo,typeFrom,typeTo,startX,startY,endX,endY);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude zmeneno a z tridy reprezentace trid presunuto do tridy {@link ClassDiagram}
     */
    public Relationships addRelationship(String nameClassTo){
        Relationships relationships = new Relationships(nameClassTo);
        relationshipsList.add(relationships);
        return relationships;
    }

    /**
     * @deprecated bude zmeneno a z tridy reprezentace trid presunuto do tridy {@link ClassDiagram}
     */
    public void relationshipDelete(Relationships relationships){
        this.relationshipsList.remove(relationships);
    }

    /**
     * @deprecated bude zmeneno a z tridy reprezentace trid presunuto do tridy {@link ClassDiagram}
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
