package ija.diagram.classdiagram.controller;


import ija.diagram.classdiagram.view.ViewClass;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ViewClassController{

    private final ControllerMain controllerMain;

    public ViewClassController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    public void classDraggedMouse(MouseEvent mouseEvent){
        if(mouseEvent.getButton() == MouseButton.MIDDLE) {
            ViewClass viewClass = (ViewClass) mouseEvent.getSource();
            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();
            double nx = viewClass.getLayoutX() - (viewClass.getWidth() / 2) + X;
            double ny = viewClass.getLayoutY() + Y;
            if (nx < 0 && ny > 0) {
                viewClass.setLayoutY(ny);
            } else if (ny < 0 && nx > 0) {
                viewClass.setLayoutX(nx);
            } else if (nx > 0 && ny > 0) {
                viewClass.setLayoutX(nx);
                viewClass.setLayoutY(ny);
            }
        }

    }

//    public static void classMenu(MouseButton mouseButton){
//
//    }


    public void classMenu(MouseEvent mouseButton) {
        if(mouseButton.getButton() == MouseButton.SECONDARY){
            System.out.println("Hello");
        }
    }
}