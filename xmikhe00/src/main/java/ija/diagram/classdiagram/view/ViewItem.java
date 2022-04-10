package ija.diagram.classdiagram.view;

import ija.diagram.classdiagram.controller.ClassContextController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public interface ViewItem {

    public void mainConstruct(ClassContextController classContextController);

    public ChoiceBox<String> getAccessModifier();

    public TextField getArgumentName();

    public VBox getParentItem();
}
