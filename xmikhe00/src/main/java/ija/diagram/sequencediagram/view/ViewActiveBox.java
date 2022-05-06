package ija.diagram.sequencediagram.view;

import ija.diagram.sequencediagram.model.Message;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class ViewActiveBox extends Pane {

    private Map<ViewMessage, Message> messageLineMap = new HashMap<ViewMessage, Message>();

    public ViewActiveBox(){
        super();
        super.setId("chunkVisible");
        super.setPrefWidth(26);
        super.setPrefHeight(30);
    }


    public ViewMessage addViewMessage(Message message){
        ViewMessage viewMessage = new ViewMessage(message.getLen(), message.getMessageType());
        viewMessage.setElement();
        viewMessage.setText(message.getName());
        viewMessage.setLayoutX(message.getX());
        viewMessage.setLayoutY(message.getY());
        messageLineMap.put(viewMessage,message);
        return viewMessage;
    }

    public void setX(double x){
        super.setLayoutX(x);
    }
    public void setY(double y){
        super.setLayoutY(y);
    }
}
