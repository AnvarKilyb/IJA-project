package ija.diagram.sequencediagram.model;

public class Message {
    public enum MessageType{
        SYNCHRONOUS,
        ASYNCHRONOUS,
        ASYNCHRONOUSRETURN,
        ASYNCHRONOUSCREATE,
        REPLY,
        DELETE
    }
    private String name;
    private String nameTo;
    private String nameFrom;
    private MessageType type;

    public Message(String name, MessageType type){
        this.name = name;
        this.type = type;
    }
    public Message(MessageType type){
        this.type = type;
    }
    public Message(String name, MessageType type, String nameTo, String nameFrom){
        this.name = name;
        this.type = type;
        this.nameTo = nameTo;
        this.nameFrom = nameFrom;
    }

    public void setName(String name) {this.name = name;}

    public void setType(MessageType type) {this.type = type;}

    public void setNameFrom(String nameFrom) {this.nameFrom = nameFrom;}

    public void setNameTo(String nameTo) {this.nameTo = nameTo;}

    public String getName() {return name;}

    public MessageType getType() {return type;}

    public String getNameTo() {return nameTo;}

    public String getNameFrom() {return nameFrom;}
}
