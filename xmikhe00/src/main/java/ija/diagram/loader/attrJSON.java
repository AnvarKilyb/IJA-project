package ija.diagram.loader;

import ija.diagram.classdiagram.model.Item;

public class attrJSON {

//    public enum AccessModifiers{
//        PRIVATE,    // -
//        PUBLIC,     // +
//        PROTECTED,  // #
//        VISIBLE,    // ~
//    }
    public enum Type{
        STRING,
        INT,
        BOOL
    }
    public Item.AccessModifier accessModifiers;
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public Item.AccessModifier getAccessModifiers() {
        return accessModifiers;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessModifiers(Item.AccessModifier accessModifiers) {
        this.accessModifiers = accessModifiers;
    }

    public void setType(String type) {
        this.type = type;
    }
}
