package ija.diagram.sequencediagram.model;

import ija.diagram.classdiagram.model.DClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SObject {
    private String name = "";
    private DClass thisClass;
    private double X = 0;
    private double Y = 30;
    private ActivationBox activationBox = null;

    public SObject(){
        this.name = "";
    }

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

    public DClass getThisClass() {
        return thisClass;
    }

    public void setThisClass(DClass thisClass) {
        this.thisClass = thisClass;
    }

    public ActivationBox addActiveBox(){
        activationBox = new ActivationBox();
        return activationBox;
    }

    public ActivationBox addActiveBox(Message message){
        activationBox = new ActivationBox();
        activationBox.setY(message.getY());
        return activationBox;
    }
    public void addActiveBox(ActivationBox activationBox){
        this.activationBox = activationBox;
    }

    public ActivationBox getActivationBox(){
        return this.activationBox;
    }


}
