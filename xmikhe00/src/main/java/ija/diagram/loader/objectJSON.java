package ija.diagram.loader;

import java.util.ArrayList;
import java.util.List;

public class objectJSON {
    enum ItemType{
        CLASS,
        CONNECTION
    }
    enum ConType{
        ASSOCIATION,
        REFLEXIVE_ASSOCIATION,
        AGGREGATION,
        COMPOSITION,
        INHERITANCE,
        NONE
    }
    enum Notation{
        ONE_TO_NONE,
        ZERO_MANY_TO_NONE,
        ONE_MANY_TO_NONE,
        ONE_TO_ONE,
        ONE_TO_ONE_MANY,
        ONE_TO_ZERO_MANY,
        ONE_MANY_TO_ONE,
        ONE_MANY_TO_ONE_MANY,
        ONE_MANY_TO_ZERO_MANY,
        ZERO_MANY_TO_ONE,
        ZERO_MANY_TO_ONE_MANY,
        ZERO_MANY_TO_ZERO_MANY,
        NONE
    }
    String name;
    ItemType type;
    ArrayList<attrJSON> attributes = new ArrayList<>();
    ArrayList<attrJSON> operations = new ArrayList<>();
    ConType conType;
    String startConnection;
    String endConnection;
    Notation notation;
    int height;
    int width;

    public void addOperation(attrJSON operation){
        this.operations.add(operation);

    }
    public void addAttribute(attrJSON attribute){
        this.attributes.add(attribute);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    public void setOperations(ArrayList<attrJSON> operations) {
        this.operations = operations;
    }

    public List<attrJSON> getOperations() {
        return operations;
    }

    public void setEndConnection(String endConnection) {
        this.endConnection = endConnection;
    }

    public String getEndConnection() {
        return endConnection;
    }

    public void setStartConnection(String startConnection) {
        this.startConnection = startConnection;
    }

    public String getStartConnection() {
        return startConnection;
    }

    public void setAttributes(ArrayList<attrJSON> attributes) {
        this.attributes = attributes;
    }

    public List<attrJSON> getAttributes() {
        return attributes;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public ConType getConType() {
        return conType;
    }

    public void setConType(ConType conType) {
        this.conType = conType;
    }

    public void setNotation(Notation notation) {
        this.notation = notation;
    }

    public Notation getNotation() {
        return notation;
    }
}
