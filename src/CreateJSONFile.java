import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateJSONFile {
    @SuppressWarnings("unchecked")
    public void create_json() {
        //First Employee
        JSONObject person1 = new JSONObject();
        person1.put("firstName", "Lokesh");
        person1.put("lastName", "Gupta");
        person1.put("age", "55");
        //person1.put("weight", "50");
        person1.put("BMI", "22.2");
        //person1.put("length", "150");

        List<String> list = Arrays.asList("altretamine","amiodarone tablet","amitriptyline tablet");
        person1.put("medicine", list);


        //Second Employee
        JSONObject person2 = new JSONObject();
        person2.put("firstName", "Brian");
        person2.put("lastName", "Schultz");
        person2.put("age", "53");
        person2.put("weight", "90");
        person2.put("BMI", "27.8");
        person2.put("length", "180");


        //third patient
        JSONObject person3 = new JSONObject();
        person3.put("firstName", "Thijmen");
        person3.put("lastName", "Geurtsen");
        person3.put("age", "19");
        person3.put("weight", "74");
        person3.put("BMI", "20.5");
        person3.put("length", "190");

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(person1);
        employeeList.add(person2);
        employeeList.add(person3);

        //lots of people




        //Write JSON file
        try (FileWriter file = new FileWriter("data.json")) {

            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        append("Dwayne Woodward");
        append("Maeve Lindsey");
        append("Alanna Villanueva");
        append("Alonso Andrews");
        append("Marley Cochran");
        append("Madison Mcgrath");
        append("Reagan Berger");
        append("Shaylee Carrillo");
        append("Ashlyn Mccarty");
        append("Mollie Downs");
        append("Emery Hardin");
        append("Jamarcus Costa");
        append("Maci Hodges");
        append("Kristina Weaver");
        append("Draven Curry");
        append("Arianna Lambert");
        append("Gillian Mcclure");
        append("Brycen Hill");
        append("Briana Avery");
        append("Seamus Alvarado");
        append("Shannon Valencia");
        append("Maximillian Sims");
        append("Fisher Crosby");
        append("Bianca Burton");

    }
    private void append(String Name){

        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader("data.json"));
            JSONArray jsonArray = (JSONArray) obj;
            String[] splited = Name.split("\\s+");

            JSONObject person = new JSONObject();
            person.put("firstName", splited[0]);
            person.put("lastName", splited[1]);
            jsonArray.add(person);

            FileWriter file = new FileWriter("data.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}
