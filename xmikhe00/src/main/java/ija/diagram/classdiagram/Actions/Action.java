package ija.diagram.classdiagram.Actions;

import ija.diagram.classdiagram.model.ClassDiagram;
import ija.diagram.classdiagram.view.ViewDiagram;

public interface Action {
    void run();
    void undo();
}
