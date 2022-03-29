package ija.diagram.classdiagram;

import java.util.List;

public class Diagram{
    private List<Class> classList;

    public void addClass(String name){
        Class cl  = returnClass(name);
        if(cl != null){
            //todo warning class exist
            return;
        }
        classList.add(new Class(name));
    }

    public void classChange(String name){
        Class cl = returnClass(name);
        cl.setName(name);
    }

    public void classDelete(String name){
        this.classList.remove(returnClass(name));
    }

    public Class returnClass(String name){
        for(Class cl: classList){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }
}
