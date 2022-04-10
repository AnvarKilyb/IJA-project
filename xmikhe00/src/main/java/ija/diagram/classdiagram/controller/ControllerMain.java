package ija.diagram.classdiagram.controller;
import ija.diagram.classdiagram.view.ViewClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ControllerMain {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonLoad;

    @FXML
    private Button buttonSave;

    @FXML
    private Pane mainPane;
//    private AnchorPane mainPane;

    public void initialize(){
        buttonAdd.addEventFilter(ActionEvent.ACTION,this::addClassAction);
    }

    private void addClassAction(ActionEvent event){
        ViewClass viewclass = new ViewClass();
        viewclass.show();
        this.mainPane.getChildren().add(viewclass);
    }

//    public static void classMouseMoved(MouseEvent mouseEvent){
//        ViewClass a =(ViewClass) mouseEvent.getSource();
////        a.write();
//        new Thread(() ->{
//            double X;
//            double Y;
//            if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
//                X = mouseEvent.getX();
//                Y = mouseEvent.getY();
//
//            }else if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){
//                a.setLayoutX(mouseEvent.getSceneX() - X;
//                a.setLayoutY(mouseEvent.getSceneX() - Y;
//            }
//        } ).start();
//
//    }
}
