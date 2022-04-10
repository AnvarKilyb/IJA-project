package ija.diagram.classdiagram.model;

abstract public class Item extends Element{
    public enum AccessModifier{
        PRIVATE,    // -
        PUBLIC,     // +
        PROTECTED,  // #
        VISIBLE,    // ~
    }
    public AccessModifier accessModifier;

    public Item(String name, AccessModifier accessModifier) {
        super(name);
        this.accessModifier = accessModifier;
    }

    public void setAccessModifier(AccessModifier accessModifier){
        this.accessModifier = accessModifier;
    }

    public AccessModifier getAccessModifier(){
        return accessModifier;
    }

    public static String returnString(AccessModifier accessModifier){
        switch (accessModifier){
            case PRIVATE:
                return "-";
            case PROTECTED:
                return  "#";
            case PUBLIC:
                return  "+";
            case VISIBLE:
                return  "~";
            //todo add method, for read warning if access modifier not correct
        }
        return "+";
    }
}
