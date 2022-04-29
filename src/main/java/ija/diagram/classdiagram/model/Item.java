package ija.diagram.classdiagram.model;

/**
 * Abstraktní třída reprezentuje jednotlivý item v třídě
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
abstract public class Item extends Element{

    public AccessModifier accessModifier;

    public enum AccessModifier{
        PRIVATE,    // -
        PUBLIC,     // +
        PROTECTED,  // #
        VISIBLE,    // ~
    }

    public Item(String name, AccessModifier accessModifier) {
        super(name);
        this.accessModifier = accessModifier;
    }

    public Item(){
        super();
        this.accessModifier = AccessModifier.PUBLIC;
    }

    public void setAccessModifier(AccessModifier accessModifier){
        this.accessModifier = accessModifier;
    }

    public AccessModifier getAccessModifier(){
        return accessModifier;
    }

    /**
     * Metoda převádí enum typ v String
     * @param accessModifier enum přístupový modifikátor
     * @return String
     */
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
