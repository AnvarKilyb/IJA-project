package ija.diagram.classdiagram;

public class Relationships{
    private String nameFrom; // todo String ?
    private String nameTo;   // todo String ?
    enum Type{
        ASSOCIATION,
        REFLEXIVEASSOCIATION,
        MULTIPLICITY,
        AGGREGATION,
        COMPOSITION,
        INHERITANCE_GENERALIZATION,
        REALIZATION,
    }
    private Type typeFrom;
    private Type typeTo;

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
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
}
