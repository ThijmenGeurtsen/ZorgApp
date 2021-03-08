import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class JSON {
    public static String json_file = "data.json";
    ////////////////////////////////////////////////
    ///index: the selected profile
    //variable: the variable you want to edit of the index
    //updateTo: it will update the selected variable to this
    public void updateVariable(int index, String variable,String updateTo){

        JSONArray jsonArray = get_json(json_file); // create array from json file
        JSONObject profile = (JSONObject) jsonArray.get(index);
        profile.put(variable,updateTo);

        //write away array to json file
        try {
            FileWriter file = new FileWriter(json_file);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ///////////////////////////
    //gets data depended on input
    /////////////////////////////
    public String get_string_i(int index, String i){
        JSONArray jsonArray = get_json(json_file);
        JSONObject Array = (JSONObject) jsonArray.get(index);
        String selected = (String) Array.get(i);
        if (selected == null){
            return "has not been found";
        }
        return selected;
    }
    ///////////////////////////
    ///generate list of patients
    ///////////////////////////
    public List<String> list_patients() {
        JSONArray jsonArray = get_json(json_file);

        //make list of names
        List<String> names = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject profile = (JSONObject) jsonArray.get(i);
            names.add(profile.get("firstName") + " " + profile.get("lastName"));
        }
        return names;
    }

    //gets list of medication
    public List<String> getStringList(int index,String type){
        JSONArray jsonArray = get_json(json_file);
        JSONObject profile = (JSONObject) jsonArray.get(index);
        List<String> output = (List<String>) profile.get(type);

        return output;
    }
    public List<String> generateList(String type) {
        JSONArray jsonArray = get_json(json_file);

        //make list of names
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject profile = (JSONObject) jsonArray.get(i);
            list.add((String) profile.get(type));
        }
        return list;
    }
    public List<Integer> getIntergerList(int index,String type){
        JSONArray jsonArray = get_json(json_file);
        JSONObject profile = (JSONObject) jsonArray.get(index);
        List<Integer> output = (List<Integer>) profile.get(type);

        return output;
    }
    public JSONObject getJSONObject(int index, String type){
        JSONArray jsonArray = get_json(json_file);
        JSONObject profile = (JSONObject) jsonArray.get(index);
        JSONObject output = (JSONObject) profile.get(type);

        return output;
    }
    public boolean check_name(String name) {
        List<String> names = list_patients();
        boolean contain = names.contains(name);
        return contain;
    }

    //////////////////////////////////
    //returns the index of the name
    /////////////////////////////////
    public int name_index(String name) {
        List<String> names = list_patients();

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }



    //////////////////////////////////////////
    ////returns json array
    /////////////////////////////////////////
    public JSONArray get_json(String FileName) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(FileName));
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


