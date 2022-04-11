package ija.diagram.loader;

import java.io.*;
import java.util.ArrayList;

import ija.diagram.classdiagram.model.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class parser {
    /**
     * Method parseJSON()
     *      Parse JSON file from input.json.
     *      Creates list of objects, that are represented as classes and relationships.
     *      Each specified object contains:
     *          name, type(class,relationship), list of attributes, list of methods, height, width, x Axis, y Axis.
     *          start relations, end relation, relation type(association, aggregation, e.t.c).
     *      Returns above mentioned list.
    * */
    public static ArrayList<objectJSON> parseJSON(){
        ArrayList<objectJSON> itemList = new ArrayList<>();
        JSONObject objectConnection = new JSONObject();
        JSONObject objectClass = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("/home/goofy/study/iJA/IJA-project/xmikhe00/src/main/resources/input.json")){
            Object objects = jsonParser.parse(reader);

            JSONArray jsonArr = (JSONArray) objects;
            if(checkJSON(jsonArr) == -1)
                return null;
            //Objects parse
            for(int i = 0; i < jsonArr.size(); i++){
                objectJSON newObject = new objectJSON();
                JSONObject object = (JSONObject) jsonArr.get(i);
                objectClass = (JSONObject) object.get("class");
                if(objectClass == null){
                    objectConnection = (JSONObject) object.get("relation");
                }
                //Classes parse
                if(objectClass != null){
                    newObject.setType(objectJSON.ItemType.CLASS);
                    String name = (String) objectClass.get("name");
                    newObject.setName(name);
                    JSONArray operations = (JSONArray) objectClass.get("operations");
                    //Operations parse
                    if(operations != null){
                        for(int j = 0; j < operations.size(); j++){
                            attrJSON aux = new attrJSON();
                            JSONObject tmp = (JSONObject) operations.get(j);
                            String id = (String) tmp.get("id");
                            if(id == null)
                                return null;
                            aux.setName(id);
                            String accessModifiers = (String) tmp.get("accessModifiers");
                            if(accessModifiers == null)
                                aux.setAccessModifiers(null);
                            else{
                                switch (accessModifiers){
                                    case "public":
                                        aux.setAccessModifiers(Item.AccessModifier.PUBLIC);
                                        break;
                                    case "protected":
                                        aux.setAccessModifiers(Item.AccessModifier.PROTECTED);
                                        break;
                                    case "private":
                                        aux.setAccessModifiers(Item.AccessModifier.PRIVATE);
                                        break;
                                    case "visible":
                                        aux.setAccessModifiers(Item.AccessModifier.VISIBLE);
                                        break;
                                }
                            }
                            String type = (String) tmp.get("type");
                            if(type == null){
                                aux.setType(null);
                            }else{
                                aux.setType(type);
                            }
                            newObject.addOperation(aux);
                        }
                    }
                    else{
                        newObject.setOperations(null);
                    }
                    //Attributes parse
                    JSONArray attributes = (JSONArray) objectClass.get("attributes");
                    if(attributes != null){
                        for(int j = 0; j < attributes.size(); j++){
                            attrJSON aux = new attrJSON();
                            JSONObject tmp = (JSONObject) attributes.get(j);
                            String id = (String) tmp.get("id");
                            if(id == null)
                                return null;
                            aux.setName(id);
                            String accessModifiers = (String) tmp.get("accessModifiers");
                            if(accessModifiers == null)
                                aux.setAccessModifiers(null);
                            else{
                                switch (accessModifiers) {
                                    case "public":
                                        aux.setAccessModifiers(Item.AccessModifier.PUBLIC);
                                        break;
                                    case "protected":
                                        aux.setAccessModifiers(Item.AccessModifier.PROTECTED);
                                        break;
                                    case "private":
                                        aux.setAccessModifiers(Item.AccessModifier.PRIVATE);
                                        break;
                                    case "visible":
                                        aux.setAccessModifiers(Item.AccessModifier.VISIBLE);
                                        break;
                                }
                            }
                            String type = (String) tmp.get("type");
                            if(type == null){
                                aux.setType(null);
                            }else{
                                aux.setType(type);
                            }
                            newObject.addAttribute(aux);
                        }
                    }
                    else{
                        newObject.setAttributes(null);
                    }
                    if(objectClass.get("height") == null){
                        newObject.setHeight(0);
                    }
                    else{
                        long height = (long) objectClass.get("height");
                        newObject.setHeight((int) height);
                    }
                    if(objectClass.get("width") == null){
                        newObject.setWidth(0);
                    }
                    else{
                        long width = (long) objectClass.get("width");
                        newObject.setWidth((int) width);
                    }
                    if(objectClass.get("xAxis") == null){
                        newObject.setxAxis(0);
                    }
                    else{
//                        System.out.println(objectClass.get("xAxis").getClass());
                        double x = Double.parseDouble((objectClass.get("xAxis")).toString());
                        newObject.setxAxis((double) x);
                    }
                    if(objectClass.get("yAxis") == null){
                        newObject.setyAxis(0);
                    }
                    else{
                        double y =  Double.parseDouble(objectClass.get("yAxis").toString());
                        newObject.setyAxis((double) y);
                    }



                }
                //Connections parse
                else if(objectConnection != null){
                    newObject.setType(objectJSON.ItemType.CONNECTION);
                    String name = (String) objectConnection.get("name");
                    newObject.setName(name);
                    String start = (String) objectConnection.get("start");
                    String end = (String) objectConnection.get("end");
                    String type = (String) objectConnection.get("type");
                    newObject.setStartConnection(start);
                    newObject.setEndConnection(end);
                    if(type == null)
                        newObject.setConType(objectJSON.ConType.NONE);
                    else{
                        switch (type) {
                            case "association":
                                newObject.setConType(objectJSON.ConType.ASSOCIATION);
                                break;
                            case "reflexive":
                                newObject.setConType(objectJSON.ConType.REFLEXIVE_ASSOCIATION);
                                break;
                            case "aggregation":
                                newObject.setConType(objectJSON.ConType.AGGREGATION);
                                break;
                            case "composition":
                                newObject.setConType(objectJSON.ConType.COMPOSITION);
                                break;
                            case "inheritance":
                                newObject.setConType(objectJSON.ConType.INHERITANCE);
                                break;
                            case "multiplicity":
                                newObject.setConType(objectJSON.ConType.MULTIPLICITY);
                                break;
                            case "realization":
                                newObject.setConType(objectJSON.ConType.REALIZATION);
                                break;
                        }
                    }
                    String notation = (String) objectConnection.get("notation");
                    if(notation == null)
                        newObject.setNotation(objectJSON.Notation.NONE);
                    else{
                        switch(notation){
                            case "1":
                                newObject.setNotation(objectJSON.Notation.ONE_TO_NONE);
                                break;
                            case "0..*":
                                newObject.setNotation(objectJSON.Notation.ZERO_MANY_TO_NONE);
                                break;
                            case "1..*":
                                newObject.setNotation(objectJSON.Notation.ONE_MANY_TO_NONE);
                                break;
                            case "1--1":
                                newObject.setNotation(objectJSON.Notation.ONE_TO_ONE);
                                break;
                            case "1--1..*":
                                newObject.setNotation(objectJSON.Notation.ONE_TO_ONE_MANY);
                                break;
                            case "1--0..*":
                                newObject.setNotation(objectJSON.Notation.ONE_TO_ZERO_MANY);
                                break;
                            case "1..*--1":
                                newObject.setNotation(objectJSON.Notation.ONE_MANY_TO_ONE);
                                break;
                            case "1..*--1..*":
                                newObject.setNotation(objectJSON.Notation.ONE_MANY_TO_ONE_MANY);
                                break;
                            case "1..*--0..*":
                                newObject.setNotation(objectJSON.Notation.ONE_MANY_TO_ZERO_MANY);
                                break;
                            case "0..*--1":
                                newObject.setNotation(objectJSON.Notation.ZERO_MANY_TO_ONE);
                                break;
                            case "0..*--1..*":
                                newObject.setNotation(objectJSON.Notation.ZERO_MANY_TO_ONE_MANY);
                                break;
                            case "0..*--0..*":
                                newObject.setNotation(objectJSON.Notation.ZERO_MANY_TO_ZERO_MANY);
                                break;
                        }
                    }
                    /*
                    ONE_TO_NONE,            1
                    ZERO_MANY_TO_NONE,      0..*
                    ONE_MANY_TO_NONE,       1..*
                    ONE_TO_ONE,             1--1
                    ONE_TO_ONE_MANY,        1--1..*
                    ONE_TO_ZERO_MANY,       1--0..*
                    ONE_MANY_TO_ONE,        1..*--1
                    ONE_MANY_TO_ONE_MANY,   1..*--1..*
                    ONE_MANY_TO_ZERO_MANY,  1..*--0..*
                    ZERO_MANY_TO_ONE,       0..*--1
                    ZERO_MANY_TO_ONE_MANY,  0..*--1..*
                    ZERO_MANY_TO_ZERO_MANY  0..*--0..*
                     */
                }
                itemList.add(newObject);
            }

            return itemList;
        } catch (FileNotFoundException e) {
            System.err.println("Invalid input file");
            return null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Method checkJSON(JSONArray).
     *      Checks JSON file for syntax errors.
     *      Returns -1 in case of errors.
     *      Otherwise, 0.
     */
    private static int checkJSON(JSONArray jsonArr){
        for(int i = 0; i < jsonArr.size(); i++){
            JSONObject object = (JSONObject) jsonArr.get(i);
            JSONObject newObject = (JSONObject) object.get("class");
            if(newObject == null){
                newObject = (JSONObject) object.get("relation");
                if(newObject == null){
                    System.err.println("Invalid objects");
                    return -1;
                }
            }

            String name = (String) newObject.get("name");
            if(name == null){
                System.err.println("Object requires name");
                return -1;
            }
        }
        return 0;
    }
}
