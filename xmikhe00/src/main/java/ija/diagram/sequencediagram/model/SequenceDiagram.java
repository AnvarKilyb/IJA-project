package ija.diagram.sequencediagram.model;

import java.util.ArrayList;
import java.util.List;

public class SequenceDiagram {
    private List<SObject> objectList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();

    public List<Message> getMessageList() {return messageList;}

    public List<SObject> getObjectList() {return objectList;}

    public void addObject(SObject object){objectList.add(object);}

    public void addObject(String name){
        SObject obj = returnObject(name);
        if(obj != null){
            System.out.println("WARNING TODO");
            //TODO
        }
        SObject object = new SObject(name);
        objectList.add(object);
    }

    public void changeName(String name){
        SObject obj = returnObject(name);
        obj.setName(name);
    }

    public void deleteObject(String name){
        objectList.remove(returnObject(name));
    }

    public void deleteObject(SObject object){
        objectList.remove(object);
    }

    public SObject returnObject(String name){
        for(SObject obj: objectList){
            if(obj.getName().equals(name))
                return obj;
        }
        return null;
    }

    public void addMessage(Message message){messageList.add(message);}

    public void addMessage(String name, Message.MessageType type){
        Message message = new Message(name, type);
        messageList.add(message);
    }

    public void addMessage(Message.MessageType type){
        Message message = new Message(type);
        messageList.add(message);
    }

    public void deleteMessage(Message message){
        messageList.remove(message);
    }
}
