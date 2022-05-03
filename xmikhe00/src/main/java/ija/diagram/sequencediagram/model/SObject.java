package ija.diagram.sequencediagram.model;

public class SObject {
    private String name;
    private double X = 0;
    private double Y = 0;

    public SObject(){this.name = "";}

    public SObject(String name){
        this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }
}
