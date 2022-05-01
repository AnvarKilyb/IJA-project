package ija.diagram.sequencediagram.model;

public class SObject {
    private String name;

    public SObject(){this.name = "";}

    public SObject(String name){
        this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
