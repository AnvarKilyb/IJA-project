package ija.diagram.sequencediagram.model;

public class RelatedActivationBoxes {
    private ActivationBox boxLeft;
    private ActivationBox boxRight;
    private SObject startObject;
    private SObject endObject;

    public ActivationBox getBoxLeft() {
        return boxLeft;
    }

    public void setBoxLeft(ActivationBox boxLeft) {
        this.boxLeft = boxLeft;
    }

    public ActivationBox getBoxRight() {
        return boxRight;
    }

    public void setBoxRight(ActivationBox boxReight) {
        this.boxRight = boxReight;
    }

    public SObject getStartObject() {
        return startObject;
    }

    public void setStartObject(SObject startObject) {
        this.startObject = startObject;
    }

    public SObject getEndObject() {
        return endObject;
    }

    public void setEndObject(SObject endObject) {
        this.endObject = endObject;
    }
}
