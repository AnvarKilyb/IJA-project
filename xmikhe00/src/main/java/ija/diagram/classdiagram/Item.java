package ija.diagram.classdiagram;

abstract class Item extends Element{
    enum AccessModifier{
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
}
