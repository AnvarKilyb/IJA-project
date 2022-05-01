package ija.diagram.classdiagram.model;

/**
 * Třída reprezentuje vztahy mezi třídami
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Relationships{
//    private String nameClassFrom; // todo String ?
    private String nameClassTo;   // todo String ?
    private String name;
    private Type typeFrom;
    private Type typeTo;

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    /**
     * Enum přístupových modifikátoru
     */
    public enum Type{
        ASSOCIATION,
        REFLEXIVEASSOCIATION,
        MULTIPLICITY,
        AGGREGATION,
        COMPOSITION,
        INHERITANCE_GENERALIZATION,
        REALIZATION,
        }

    /**
     * Vytvoří instance vztahů
     * @param nameClassTo jméno třídy do které jde vztah
     * @param typeFrom typ začátku vztahu
     * @param typeTo typ konce vztahu
     */
    public Relationships(String nameClassTo, Type typeFrom, Type typeTo){
        this.nameClassTo = nameClassTo;
        this.typeFrom = typeFrom;
        this.typeTo = typeTo;
    }

    /**
     * Vytvoří instance vztahu
     * @param nameClassTo jméno třídy ke které jde vztah
     * @param typeFrom typ začátku vztahu
     * @param typeTo typ konce vztahu
     * @param startX souřadnice začátku
     * @param startY souřadnice začátku
     * @param endX koncová souřadnice
     * @param endY koncová souřadnice
     */
    public Relationships(String nameClassTo, Type typeFrom, Type typeTo, double startX, double startY, double endX, double endY){
        this.nameClassTo = nameClassTo;
        this.typeFrom = typeFrom;
        this.typeTo = typeTo;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Vytvoří instance vztahu
     * automaticky dodává typ přístupových modifikátoru
     * @param nameClassTo jméno třídy ke které jde vztah
     */
    public Relationships(String nameClassTo){
        this.nameClassTo = nameClassTo;
        this.typeFrom = Type.ASSOCIATION;
        this.typeTo = Type.ASSOCIATION;
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

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }
}
