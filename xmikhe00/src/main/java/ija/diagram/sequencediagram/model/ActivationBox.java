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
    private boolean isDelete = false;
    private boolean reply = false;
    private double plush = 0.0;


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
        if(reply){
            message.setY((outMessage.size() + inMessage.size()) *  16 + 16 + plush);
            plush += 16;
            reply = false;
        }else{
            message.setY((outMessage.size() + inMessage.size()) *  16 + plush);
            plush = 0;
        }
        outMessage.add(message);
        height += 16 + plush;
        return message;
    }

    public void addNewInMessage(Message message){
        if(message.getMessageType() == Message.MessageType.REPLY){
            reply = true;
        }
        if(height < message.getY()){
            height = message.getY() + 20;
        }else{
            height += 20;
        }
        inMessage.add(message);
    }

    public Message addNewOutMessageLoad(String name, Message.MessageType messageType,double X, double Y){
        Message message = new Message(name, messageType);
        if(reply){
            plush += 16;
            reply = false;
        }else{
            plush = 0;
        }
        message.setX(X);
        message.setY(Y);
        outMessage.add(message);
        return message;
    }

    public void addNewInMessageLoad(Message message){
        if(message.getMessageType() == Message.MessageType.REPLY){
            reply = true;
        }
        inMessage.add(message);
    }

    public List<Message> getInMessage() {
        return inMessage;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
