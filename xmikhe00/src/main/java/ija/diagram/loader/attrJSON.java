package ija.diagram.loader;

public class attrJSON {
    enum AccessModifiers{
        PUBLIC,
        PROTECTED,
        PRIVATE
    }
    enum Type{
        STRING,
        INT,
        BOOL
    }
    AccessModifiers accessModifiers;
    String name;
    Type type;

    public String getName() {
        return name;
    }

    public AccessModifiers getAccessModifiers() {
        return accessModifiers;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessModifiers(AccessModifiers accessModifiers) {
        this.accessModifiers = accessModifiers;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
