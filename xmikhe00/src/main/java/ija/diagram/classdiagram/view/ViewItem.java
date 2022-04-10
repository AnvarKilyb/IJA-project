package ija.diagram.classdiagram.view;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public interface ViewItem {

    public void mainConstruct();

    public ChoiceBox<String> getAccessModifier();

    public TextField getArgumentName();

    public VBox getParentItem();
}
