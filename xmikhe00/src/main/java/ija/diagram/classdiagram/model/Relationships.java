package ija.diagram.classdiagram.model;

public class Relationships{
//    private String nameClassFrom; // todo String ?
    private String nameClassTo;   // todo String ?
    private String name;
    private final int inName;
    private static int counter = 0;
    private Type typeFrom;
    private Type typeTo;
    enum Type{
        ASSOCIATION,
        REFLEXIVEASSOCIATION,
        MULTIPLICITY,
        AGGREGATION,
        COMPOSITION,
        INHERITANCE_GENERALIZATION,
        REALIZATION,
        }


    public Relationships(String nameClassTo, Type typeFrom, Type typeTo){
        this.nameClassTo = nameClassTo;
        this.typeFrom = typeFrom;
        this.typeTo = typeTo;
        this.inName = counter++;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

//    public String getNameClassFrom() {
//        return nameClassFrom;
//    }
//
//    public void setNameClassFrom(String nameClassFrom) {
//        this.nameClassFrom = nameClassFrom;
//    }

    public String getNameClassTo() {
        return nameClassTo;
    }

    public void setNameClassTo(String nameClassTo) {
        this.nameClassTo = nameClassTo;
    }

    public Type getTypeFrom() {
        return typeFrom;
    }

    public void setTypeFrom(Type typeFrom) {
        this.typeFrom = typeFrom;
    }

    public Type getTypeTo() {
        return typeTo;
    }

    public void setTypeTo(Type typeTo) {
        this.typeTo = typeTo;
    }

    public int getInName(){
      return this.inName;
    }
}
