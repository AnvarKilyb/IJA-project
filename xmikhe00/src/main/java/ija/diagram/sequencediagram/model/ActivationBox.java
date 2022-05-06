package ija.diagram.sequencediagram.model;

import ija.diagram.classdiagram.model.Methods;

import java.util.ArrayList;
import java.util.List;

public class ActivationBox{

    private SObject thisObject;
    private List<Message> outMessage = new ArrayList<>();
    private List<Message> inMessage = new ArrayList<>();
    private double x = 27;
    private double y = 90;
    private double height = 30;
    private final int weight = 26;

    public SObject getThisObject() {
        return thisObject;
    }

    public void setThisObject(SObject thisObject) {
        this.thisObject = thisObject;
    }

    public List<Message> getOutMessage() {
        return outMessage;
    }

    public void addOutMessage(Message outMessage) {
        this.outMessage.add(outMessage);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Message addNewOutMessage(String name, Message.MessageType messageType){
        Message message = new Message(name, messageType);
        message.setY((outMessage.size() + inMessage.size()) *  16 + 2);
        outMessage.add(message);
        height += 20;
        return message;
    }

    public void addNewInMessage(Message message){
        height += 20;
        inMessage.add(message);
    }
}
