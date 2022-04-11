package ija.diagram.classdiagram.controller;


import ija.diagram.classdiagram.view.ViewClass;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class ViewClassController{

    private final ControllerMain controllerMain;
//    private double lineStartX;
//    private double lineStartY;
    private Line newLine;

    public ViewClassController(ControllerMain controllerMain){
        this.controllerMain = controllerMain;
    }

    public void clickToLine(MouseEvent mouseEvent){
        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();
            ViewClass viewClass = (ViewClass) mouseEvent.getSource();
            newLine = new Line();
            if(X < 4 || X > viewClass.getWidth() - 4 || Y < 4 || Y > viewClass.getHeight() -4){
//                ViewClass viewClass = (ViewClass) mouseEvent.getSource();
                Pane mainPane = (Pane) viewClass.getParent();
                System.out.println("Xm" + X);
                System.out.println("Ym" + Y);
                System.out.println("Xcl" + viewClass.getLayoutX());
                System.out.println("Ycl" + viewClass.getLayoutX());
                newLine.setStartX(viewClass.getLayoutX() + X);
                newLine.setStartY(viewClass.getLayoutY() + Y);
                newLine.setEndX(viewClass.getLayoutX() + X);
                newLine.setEndY(viewClass.getLayoutY() + Y);
                mainPane.getChildren().add(newLine);
        //        lineStartX = mouseEvent.getX();
        //        lineStartY = mouseEvent.getY();
            }
        }
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
        }else if(mouseEvent.getButton() == MouseButton.PRIMARY){
            ViewClass viewClass = (ViewClass) mouseEvent.getSource();
            Pane mainPane = (Pane) viewClass.getParent();
            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();
            double endX = viewClass.getLayoutX() + X;
            double endY = viewClass.getLayoutY() + Y;
            if (endX < 0 && endY > 0) {
                newLine.setEndY(endY);
            } else if (endY < 0 && endX > 0) {
                newLine.setEndX(endX);
            } else if (endX > 0 && endY > 0) {
                newLine.setEndX(endX);
                newLine.setEndY(endY);
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