package ija.diagram.loader;

import java.io.*;
import java.util.ArrayList;

import ija.diagram.classdiagram.model.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    private ArrayList<objectJSON> itemList = new ArrayList<>();

    /**
     * Method parseJSON()
     *      Parse JSON file from input.json.
     *      Creates list of objects, that are represented as classes and relationships.
     *      Each specified object contains:
     *          name, type(class,relationship), list of attributes, list of methods, height, width, x Axis, y Axis.
     *          start relations, end relation, relation type(association, aggregation, e.t.c).
     *      Returns above mentioned list.
     * */
    public ArrayList<objectJSON> parseJSON(){
        JSONArray jsonArr = fileOpen();

        //Objects parse
        if(jsonArr == null){
            return null;
        }
        for (Object o : jsonArr) {
            JSONObject object = (JSONObject) o;
            JSONObject objectClass = (JSONObject) object.get("class");
            JSONObject objectConnection = (JSONObject) object.get("relation");
            //Classes parse
            boolean check = parseClass(objectClass);
            if (!check) {
                return null;
            }
            //Connections parse
            check = parseConnection(objectConnection);
            if (!check) {
                return null;
            }
        }
        return itemList;
    }


    private JSONArray fileOpen(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("/home/goofy/study/iJA/IJA-project/xmikhe00/src/main/resources/input.json")){
            Object objects = jsonParser.parse(reader);
            JSONArray jsonArr = (JSONArray) objects;
            if (checkJSON(jsonArr) == -1)
                return null;
            return jsonArr;
        } catch (FileNotFoundException e) {
            System.err.println("Invalid input file");
            return null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean setAttributeOperation(objectJSON newObject, JSONArray item, String nameItem){
        if(item != null){
            for(int j = 0; j < item.size(); j++){
                attrJSON aux = new attrJSON();
                JSONObject tmp = (JSONObject) item.get(j);
                String id = (String) tmp.get("id");
                if(id == null) {
                    return false;
                }
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
                aux.setType(type);
                if(nameItem.equals("operations")) {
                    newObject.addOperation(aux);
                }else if (nameItem.equals("attributes")){
                    newObject.addAttribute(aux);
                }
            }
        }
        else{
            if(nameItem.equals("operations")) {
                newObject.setOperations(null);
            }else if (nameItem.equals("attributes")){
                newObject.setAttributes(null);
            }
        }
        return true;
    }

    private boolean parseClass(JSONObject objectClass){
        if(objectClass != null){
            objectJSON newObject = new objectJSON();
            newObject.setType(objectJSON.ItemType.CLASS);
            String name = (String) objectClass.get("name");
            newObject.setName(name);
            JSONArray operations = (JSONArray) objectClass.get("operations");
            //Operations parse
            boolean check = setAttributeOperation(newObject, operations, "operations");
            if(!check){
                return false;
            }
            //Attributes parse
            JSONArray attributes = (JSONArray) objectClass.get("attributes");
            check = setAttributeOperation(newObject, attributes, "attributes");
            if(!check){
                return false;
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
                double x = Double.parseDouble((objectClass.get("xAxis")).toString());
                newObject.setxAxis(x);
            }
            if(objectClass.get("yAxis") == null){
                newObject.setyAxis(0);
            }
            else{
                double y =  Double.parseDouble(objectClass.get("yAxis").toString());
                newObject.setyAxis(y);
            }

            itemList.add(newObject);
        }
        return true;
    }

    private boolean parseConnection(JSONObject objectConnection){
        if(objectConnection != null){
            objectJSON newObject = new objectJSON();
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
            itemList.add(newObject);
        }
        return true;
    }

//    public static void

    /**
     * Method checkJSON(JSONArray).
     *      Checks JSON file for syntax errors.
     *      Returns -1 in case of errors.
     *      Otherwise, 0.
     */
    private static int checkJSON(JSONArray jsonArr){
        if(jsonArr == null){
            return -1;
        }
        for (Object o : jsonArr) {
            JSONObject object = (JSONObject) o;
            JSONObject newObject = (JSONObject) object.get("class");
            if (newObject == null) {
                newObject = (JSONObject) object.get("relation");
                if (newObject == null) {
                    System.err.println("Invalid objects");
                    return -1;
                }
            }

            String name = (String) newObject.get("name");
            if (name == null) {
                System.err.println("Object requires name");
                return -1;
            }
        }
        return 0;
    }
}
