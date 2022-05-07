package ija.diagram.loader;

public class ParticipantJSON {
    private String name;
    double x;
    public ParticipantJSON(String name, double x){
        this.name = name;
        this.x = x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
