package ija.diagram.loader;

import ija.diagram.classdiagram.model.Arguments;
import ija.diagram.classdiagram.model.DClass;
import ija.diagram.classdiagram.model.Methods;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Uloží diagramu třídu do JSON souboru
 * @author Vladislav Mikheda : xmikhe00
 * @author Anvar Kilybayev : xkilyb00
 * @version 0.0.5
 */
public class Writer {
    /**
     * Získá dCLassList s daty ve třídách a vypíše vše do souboru ve formátu JSON
     */
    public void writeJSON(List<DClass> dClassList){
        JSONArray mainList = new JSONArray();
        for(DClass item: dClassList){
            JSONArray attrList = new JSONArray();
            JSONArray methodList = new JSONArray();
            JSONObject classList = new JSONObject();
            JSONObject classParams = new JSONObject();
            classParams.put("name", item.getName());
            classParams.put("xAxis", item.getX());
            classParams.put("yAxis", item.getY());
            for(Arguments argument: item.getArgumentsList()){
                JSONObject attrParams = new JSONObject();
                if(argument.getAccessModifier() != null)
                    attrParams.put("accessModifiers", argument.getAccessModifier().toString().toLowerCase());
                if(argument.getType() != null)
                    attrParams.put("type", argument.getType());
                attrParams.put("id", argument.getName());
                attrList.add(attrParams);
            }
            for(Methods method: item.getMethodsList()){
                JSONObject methodParams = new JSONObject();
                if(method.getAccessModifier() != null)
                    methodParams.put("accessModifiers", method.getAccessModifier().toString().toLowerCase());
                methodParams.put("id", method.getName());
                methodList.add(methodParams);
            }
            if(item.getArgumentsList().size() != 0)
                classParams.put("attributes", attrList);
            if(item.getMethodsList().size() != 0)
                classParams.put("operations", methodList);
            classList.put("class", classParams);
            mainList.add(classList);
        }
        try (FileWriter file = new FileWriter("data/output.json")) {
            file.write(mainList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
