package ija.diagram.classdiagram;

import java.util.ArrayList;
import java.util.List;

public class DiagramClass extends Element{
    private List<Arguments> argumentsList;
    private List<Methods> methodsList;
    private List<Relationships> relationshipsList;
    //todo coordinates

    public DiagramClass(String name){
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

    public void addMethod(String name, Item.AccessModifier accessModifier){
        Methods method = returnMethod(name);
        if (method != null){
            //todo warning to gui
            return;
        }
        methodsList.add(new Methods(name,accessModifier));
    }

    public void methodChange(String name, Item.AccessModifier accessModifier,String type){
        Methods method = returnMethod(name);
        method.setName(name);
        method.setAccessModifier(accessModifier);
    }

    public void methodDelete(String name){
        this.methodsList.remove(returnMethod(name));
    }

    public Methods returnMethod(String name){
        for(Methods method: methodsList){
            if(method.getName().equals(name)){
                return method;
            }
        }

        return null;
    }


    public void addRelationship(String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo){
        relationshipsList.add(new Relationships(nameClassTo,typeFrom,typeTo));
    }

    public void relationshipChange(int inName,String name,String nameClassTo, Relationships.Type typeFrom, Relationships.Type typeTo){
        Relationships relships = returnRelationship(inName);
        relships.setName(name);
        relships.setNameClassTo(nameClassTo);
        relships.setTypeFrom(typeFrom);
        relships.setTypeTo(typeTo);

    }

    public void relationshipDelete(int inName){
        this.relationshipsList.remove(returnRelationship(inName));
    }

    public Relationships returnRelationship(int inName){
        for(Relationships relship: relationshipsList){
            if(relship.getInName() == inName){
                return relship;
            }
        }
        return null;
    }


}
