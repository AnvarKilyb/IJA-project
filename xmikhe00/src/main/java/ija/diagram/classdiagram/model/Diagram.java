package ija.diagram.classdiagram.model;

import java.util.List;

public class Diagram{
    private List<DiagramClass> diagramClassList;

    public void addClass(String name){
        DiagramClass cl  = returnClass(name);
        if(cl != null){
            //todo warning class exist
            return;
        }
        diagramClassList.add(new DiagramClass(name));
    }

    public void classChange(String name){
        DiagramClass cl = returnClass(name);
        cl.setName(name);
    }

    public void classDelete(String name){
        this.diagramClassList.remove(returnClass(name));
    }

    public DiagramClass returnClass(String name){
        for(DiagramClass cl: diagramClassList){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }
}
