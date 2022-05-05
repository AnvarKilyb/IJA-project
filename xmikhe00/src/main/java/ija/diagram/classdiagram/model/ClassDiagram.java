package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída reprezentuje diagram tříd
 * obsahuje metody pro manipulaci s jednotlivými třídami a vztahy
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ClassDiagram {
    /**Seznam pro uchovaní jednotlivých tříd*/
    private List<DClass> dClassList = new ArrayList<DClass>();
    /**Automatické jméno pro třídu vytvářenou uživatelem**/
    private int classNameCounter = 0;

    private List<Relationships> relationshipsList = new ArrayList<Relationships>();


    /**
     * Vytvoří a uchová novou třídu
     * @param name jméno třídy
     * @return vrátí vytvořenou třídu
     */
    public DClass addClass(String name){
        DClass cl  = returnClass(name);
        if(cl != null){
            //class exist
           return null;
        }
        DClass dClass = new DClass(name);
        dClassList.add(dClass);
        return dClass;
    }

    public boolean setClassName(String name, DClass dClass){
        DClass cl  = checkName(name,dClass);
        if(cl != null){
            return false;
        }
        dClass.setName(name);
        return true;
    }

    /**
     * Uchová novou třídu která byla vytvořena v jiném mistě
     */
    public void addClass(DClass dClass){
        dClassList.add(dClass);
    }

    /**
     * Vytvoří a uchová novou třídu
     * Jméno dodá automaticky
     * @return vrátí vytvořenou třídu
     */
    public DClass addClass(){
        String name = Integer.toString(classNameCounter);
        classNameCounter++;
        DClass dClass = new DClass(name);
        dClassList.add(dClass);
        return dClass;
    }

    /**
     * Změní jméno třídy
     */
    public void classChange(String name){
        DClass cl = returnClass(name);
        cl.setName(name);
    }

    /**
     * Odstraní třídu
     * @param name jméno třídy
     */
    public void classDelete(String name){
        this.dClassList.remove(returnClass(name));
    }

    /**
     * Odstraní třídu
     * @param dClass instance třídy
     */
    public void classDelete(DClass dClass){
        this.dClassList.remove(dClass);
    }

    /**
     * @return vrátí instanci třídy
     */
    public DClass returnClass(String name){
        for(DClass cl: dClassList){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }


    public DClass checkName(String name ,DClass dClass){
        for(DClass cl: dClassList){
            if(cl.getName().equals(name)){
                if (cl != dClass){
                    return cl;
                }
            }
        }
        return null;
    }


    /**
     * @return vráti seznam tříd
     */
    public List<DClass> getdClassList(){
        return dClassList;
    }

    public void deleteAll(){
        dClassList.clear();
        relationshipsList.clear();
    }



    public List<Relationships> getRelationshipsList(){
        return relationshipsList;
    }


    public void deleteRelation(Relationships relationships){
        this.relationshipsList.remove(relationships);
    }

    public Relationships addRelationship(DClass classFrom, Relationships.Type typeShip){
        Relationships relationships = new Relationships(classFrom,typeShip);
        relationshipsList.add(relationships);
        return relationships;
    }


    public Relationships addRelationship(DClass classFrom, Relationships.Type typeShip,
                                         double startX, double startY, double endX, double endY){
        Relationships relationships = new Relationships(classFrom,typeShip,startX,startY,endX,endY);
        relationshipsList.add(relationships);
        return relationships;
    }

    public Relationships addRelationship(DClass classFrom,DClass classTo, Relationships.Type typeShip,
                                         double startX, double startY, double endX, double endY){
        Relationships relationships = new Relationships(classFrom,classTo,typeShip,startX,startY,endX,endY);
        relationshipsList.add(relationships);
        return relationships;
    }


    public Relationships addRelationship(DClass classFrom){
        Relationships relationships = new Relationships(classFrom);
        relationshipsList.add(relationships);
        return relationships;
    }


    public void relationshipDelete(Relationships relationships){
        this.relationshipsList.remove(relationships);
    }

}
