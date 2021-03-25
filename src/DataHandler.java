import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {
    public void save(List<Profile> profiles) {
        try {
            FileWriter writer = new FileWriter("data.data");
            for (Profile profile : profiles) {
                List<Object> info = new ArrayList<>();
                ///info types
                info.add("firstName: " + profile.getFirstName());
                info.add("lastName: " + profile.getLastName());
                info.add("age: " + profile.getAge());
                info.add("height: " + profile.getHeight());
                List<Weight> weights = profile.getWeight();


                //save weight
                List<Object> weightAppend = new ArrayList<>();
                for (Weight weight : weights) {
                    weightAppend.add(weight.getDate()+" " + weight.getWeight());
                }
                info.add("weight: " + weightAppend);

                writer.write("End: End"+System.lineSeparator());

                //write rest of objects
                for (Object object : info) {
                    writer.write(object + System.lineSeparator());
                }

            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public void load(){

        try {
            File file = new File("data.data");
            Scanner myReader = new Scanner(file);
            List<Weight> weightList = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                Object[] parts = data.split(": ");
                String firstName;
                String lastName;
                int age;
                double height =0;

                try {
                    switch ((String) parts[0]){
                        case "End":{
                            Profile profile2 = new Profile(
                                    "Dwayne"
                                    , "Woodward"
                                    , 40
                                    , 180
                                    , 22
                                    , weights
                                    , medList);
                            System.out.println(weightList);
                            weightList.clear();
                        }
                        case "firstName":{
                            firstName = (String) parts[1];
                            break;
                        }
                        case "lastName":{
                            lastName = (String) parts[1];
                            break;
                        }
                        case  "age":{
                            age = Integer.parseInt((String) parts[1]);
                            break;
                        }
                        case "height":{
                            height = Double.parseDouble((String) parts[1]);
                            break;
                        }

                        case "weight":{
                            String partof = (String) parts[1];
                            //remove brackets
                            partof = partof.replace("[","");
                            partof = partof.replace("]","");
                            String[] info = partof.split(", ");

                            for (String weightAndDate : info){
                                String[] weightAndDateList = weightAndDate.split(" ");
                                //date
                                String date = weightAndDateList[0];
                                String[] dateList = date.split("-");
                                int year = Integer.parseInt(dateList[0]);
                                int month = Integer.parseInt(dateList[1]);
                                int day = Integer.parseInt(dateList[2]);

                                //weight
                                double weightInKg = Double.parseDouble(weightAndDateList[1]);

                                //create weight and add to list
                                Weight weight = new Weight(day, month, year, weightInKg);
                                weightList.add(weight);
                            }

                            break;

                        }

                    }

                } catch(Exception e) {
                }
            }



            myReader.close();


        }catch(Exception e){
            System.out.println(e);
        }



    }
}
