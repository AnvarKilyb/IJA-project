package ija.diagram;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class ClassItem extends HBox {

    private ChoiceBox relationShip = new ChoiceBox();
    private TextField argumentName = new TextField();
    private TextField argumentType = new TextField();

    public ClassItem(){
        super();
        relationShip.getItems().add("-");
        relationShip.getItems().add("+");
        relationShip.getItems().add("#");
        relationShip.getItems().add("~");
        argumentType.setPrefWidth(50);
        super.getChildren().add(relationShip);
        super.getChildren().add(argumentName);
        super.getChildren().add(argumentType);

    }

    public ChoiceBox getChoiceBox() {
        return relationShip;
    }

    public TextField getArgumentName() {
        return argumentName;
    }

    public TextField getArgumentType() {
        return argumentType;
    }
}
