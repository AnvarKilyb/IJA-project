package ija.diagram.classdiagram.model;

public class Arguments extends Item{
    public String type;

    public Arguments(String name, AccessModifier accessModifier) {
        super(name,accessModifier);
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
