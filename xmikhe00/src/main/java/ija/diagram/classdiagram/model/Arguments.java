package ija.diagram.classdiagram.model;


/**
 * Argument tridy
 * obsahuje metody pro dadani argumentu a jeho zmeny
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Arguments extends Item{
    public String type;

    public Arguments(String name, AccessModifier accessModifier) {
        super(name,accessModifier);
    }

    public Arguments(){
        super();
        type = "";
    }
    public void setType(String type){
        this.type = type;
    }

    /**
     * @return typ argumentu
     */
    public String getType(){
        return this.type;
    }
}
