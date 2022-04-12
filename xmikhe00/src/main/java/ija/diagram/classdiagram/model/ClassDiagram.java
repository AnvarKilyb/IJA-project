package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida reprezentuje diagramu trid
 * obsahuje metody pro manipulave s jednotlivami tridami a vzthami
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class ClassDiagram {
    /**
     * Seznam pro uchovani jednotlivych trid
     */
    private List<DClass> dClassList = new ArrayList<DClass>();
    /**Automaticke jmeno pro tridu vytvarenu uzivatelem**/
    private int classNameCounter = 0;

    /**
     * Vytvori a uchova novou tridu
     * @param name jmeno tridy
     * @return vrati vytvorenou tridu
     */
    public DClass addClass(String name){
        DClass cl  = returnClass(name);
        if(cl != null){
            System.out.println("todo");
            //todo warning class exist
        }
        DClass dClass = new DClass(name);
        dClassList.add(dClass);
        return dClass;
    }

    /**
     * Uchova novou tridu ktera byla vytvorina v jinem miste
     */
    public void addClass(DClass dClass){
        dClassList.add(dClass);
    }

    /**
     * Vytvori a uchova novou tridu
     * Jmeno doda automaticke
     * @return vrati vytvorenou tridu
     */
    public DClass addClass(){
        String name = Integer.toString(classNameCounter);
        classNameCounter++;
        DClass dClass = new DClass(name);
        dClassList.add(dClass);
        return dClass;
    }

    /**
     * Zmeni jmeno tridy
     */
    public void classChange(String name){
        DClass cl = returnClass(name);
        cl.setName(name);
    }

    /**
     * Odstrani tridu
     * @param name jmeno tridy
     */
    public void classDelete(String name){
        this.dClassList.remove(returnClass(name));
    }

    /**
     * Odstrani tridu
     * @param dClass instance tridy
     */
    public void classDelete(DClass dClass){
        this.dClassList.remove(dClass);
    }

    /**
     * @return vrati instance tridy
     */
    public DClass returnClass(String name){
        for(DClass cl: dClassList){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }

//    public void writeAll(){
//        for(DClass dClass : dClassList){
//            System.out.println(dClass.getName());
//            for(Arguments arguments : dClass.getArgumentsList()){
//                System.out.println(Item.returnString(arguments.getAccessModifier()));
//            }
//        }
//    }

    /**
     * @return vrati seznam trid
     */
    public List<DClass> getdClassList(){
        return dClassList;
    }
}
