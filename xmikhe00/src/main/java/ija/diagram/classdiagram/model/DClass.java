package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

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

    public Arguments addArgument(String name, Item.AccessModifier accessModifier,String type ){
        Arguments newArg = new Arguments(name,accessModifier);
        newArg.setType(type);
        argumentsList.add(newArg);
        return newArg;
    }

    public Arguments addArgument(){
        Arguments newArg = new Arguments();
        argumentsList.add(newArg);
        return newArg;
    }

    public void argumentDelete(Arguments argument){
        this.argumentsList.remove(argument);
    }

    public Methods addMethod(String name, Item.AccessModifier accessModifier){
        Methods methods = new Methods(name,accessModifier);
        methodsList.add(methods);
        return methods;
    }

    public Methods addMethod(){
        Methods methods = new Methods();
        methodsList.add(methods);
        return methods;
    }


    public void methodDelete(Methods method){
        this.methodsList.remove(method);
    }

    public Relationships addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo){
        Relationships relationships = new Relationships(nameClassTo,typeFrom,typeTo);
        relationshipsList.add(relationships);
        return relationships;
    }

    public Relationships addRelationship(String nameClassTo){
        Relationships relationships = new Relationships(nameClassTo);
        relationshipsList.add(relationships);
        return relationships;
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

    public double setY(){
        return this.Y;
    }

    public void relationshipDelete(Relationships relationships){
        this.relationshipsList.remove(relationships);
    }


}
