package ija.diagram.classdiagram;

import java.util.ArrayList;
import java.util.List;

public class Class extends Element{
    private List<Arguments> argumentsList;
    private List<Methods> methodsList;
    private List<Relationships> relationshipsList;

    public Class(String name){
        super(name);
        argumentsList = new ArrayList<Arguments>();
        methodsList = new ArrayList<Methods>();
        relationshipsList = new ArrayList<Relationships>();
    }

    public void addArgument(String name, Item.AccessModifier accessModifier,String type ){
        Arguments arg = returnArgument(name);
        if (arg != null){
            //todo warning to gui
            return;
        }
        Arguments newArg = new Arguments(name,accessModifier);
        newArg.setType(type);
        argumentsList.add(newArg);
    }

    public void argumentChange(String name, Item.AccessModifier accessModifier,String type){
        Arguments arg = returnArgument(name);
        arg.setName(name);
        arg.setType(type);
        arg.setAccessModifier(accessModifier);
    }

    public void argumentDelete(String name){
        this.argumentsList.remove(returnArgument(name));
    }

    public Arguments returnArgument(String name){
        for(Arguments arg: argumentsList){
            if(arg.getName().equals(name)){
                return arg;
            }
        }

        return null;
    }

}
