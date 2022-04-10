package ija.diagram.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

public class ClassDiagram {
    private List<DClass> dClassList = new ArrayList<DClass>();
    private int classNameCounter = 0;

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

    public DClass addClass(){
        String name = Integer.toString(classNameCounter);
        classNameCounter++;
        DClass dClass = new DClass(name);
        dClassList.add(dClass);
        return dClass;
    }

    public void classChange(String name){
        DClass cl = returnClass(name);
        cl.setName(name);
    }

    public void classDelete(String name){
        this.dClassList.remove(returnClass(name));
    }

    public void classDelete(DClass dClass){
        this.dClassList.remove(dClass);
    }

    public DClass returnClass(String name){
        for(DClass cl: dClassList){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }

    public void writeAll(){
        for(DClass dClass : dClassList){
            System.out.println(dClass.getName());
            for(Arguments arguments : dClass.getArgumentsList()){
                System.out.println(Item.returnString(arguments.getAccessModifier()));
            }
        }
    }
}
