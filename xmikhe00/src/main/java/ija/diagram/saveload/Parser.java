//package ija.diagram.saveload;
//
//import java.io.*;
//import java.io.IOException;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class Parser {
//
////    public static void main(String[] args){
////        JSONArray parsedList = parseJSON();
//////        if(parsedList == null)
//////            return;
//////        checkJSON(parsedList);
//////        System.out.println(parsedList);
////
////    }
//
//    static JSONArray parseArr;
//    static FileReader openFile;
//
//    static void parseJSON(String fileName){
////        "/home/goofy/study/iJA/IJA-project/xmikhe00/src/main/resources/input.json"
//        try(FileReader reader = new FileReader(fileName)){ //todo
//           this.openFile = reader;
//        }catch (FileNotFoundException e) {
//            System.err.println("Invalid input file");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static JSONArray parseJSON(){
//        JSONParser jsonParser = new JSONParser();
//
//        try(FileReader reader = new FileReader("/home/goofy/study/iJA/IJA-project/xmikhe00/src/main/resources/input.json")){ //todo
//            JSONArray array = (JSONArray) jsonParser.parse(reader);
//            for(Object o: array){
//                JSONObject cl = (JSONObject) o;
//                JSONObject c = (JSONObject) cl.get("class");
//                String name = (String) c.get("name");
//                JSONArray operations = (JSONArray) c.get("operations");
//                for(Object no : operations){
//                    JSONObject as = (JSONObject) no;
//                    String id = (String) as.get("id");
//                    System.out.println(id);
//                }
//                JSONArray attribute = (JSONArray) c.get("attribute");
////                for(Object a: c){
////
////                }
//                System.out.println(name);
//            }
//            return null;
//        } catch (FileNotFoundException e) {
//            System.err.println("Invalid input file");
//            return null;
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static int checkJSON(JSONArray jsonArr){
//        for (Object o : jsonArr) {
//            JSONObject object = (JSONObject) o;
//            JSONObject newObject = (JSONObject) object.get("class");
//            if (newObject == null) {
//                newObject = (JSONObject) object.get("connection");
//                if (newObject == null) {
//                    System.err.println("Invalid objects");
//                    return -1;
//                }
//            }
//
//            String name = (String) newObject.get("name");
//            if (name == null) {
//                System.err.println("Object requires name");
//            }
//        }
//        return 0;
//    }
//}
