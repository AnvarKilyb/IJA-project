package ija.diagram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
//        mainPane.getChildren().add(new Label("Hello"));
        ViewClass newclass = new ViewClass();;
        mainPane.getChildren().add(newclass);
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
