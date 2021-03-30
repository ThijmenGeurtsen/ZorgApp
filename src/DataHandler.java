import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {
    public void savePatients(List<Profile> profiles) {
        try {
            FileWriter writer = new FileWriter("data.data");
            for (Profile profile : profiles) {
                List<Object> info = new ArrayList<>();
                ///info types
                info.add("firstName: " + profile.getFirstName());
                info.add("lastName: " + profile.getLastName());
                info.add("age: " + profile.getAge());
                info.add("height: " + profile.getHeight());



                //save weight
                List<Weight> weights = profile.getWeight();
                List<Object> weightAppend = new ArrayList<>(); //list to append to info list
                for (Weight weight : weights) {
                    weightAppend.add(weight.getDate()+" " + weight.getWeight());
                }

                info.add("weight: " + weightAppend);

                //save medicine
                List<Medicine> meds = profile.getMedicine();
                List<Object> medsAppend = new ArrayList<>();
                for (Medicine medicineObject : meds){
                    String medName = medicineObject.getName();
                    String medDesc = medicineObject.getDescription();
                    double medDosis = medicineObject.getDosis();
                    Object medicine = (medName+"-/"+medDesc+"-/"+medDosis+"-");
                    medsAppend.add(medicine);


                }
                info.add("medicine: "+ medsAppend);



                //write rest of objects
                for (Object object : info) {
                    writer.write(object + System.lineSeparator());
                }

                writer.write("End: End"+System.lineSeparator()); //write end so the load program know where the profile end
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public List<Profile> loadPatients(){

        List<Profile> profiles = new ArrayList<>();


        try {
            File file = new File("data.data");
            Scanner myReader = new Scanner(file);

            //initialize variables
            List<Weight> weightList = new ArrayList<>();
            List<Medicine> medList = new ArrayList<>();
            String firstName = "none";
            String lastName = "none";
            int age = 0;
            double height = 0;


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Object[] parts = data.split(": ");
                switch ((String) parts[0]) {
                    case "End": {
                        List<Weight> weightListAdd = new ArrayList<>(); // this list will be added to the profile
                        List<Medicine> medListAdd = new ArrayList<>();

                        //make copy of weight list which will be added to profile.
                        for (Weight weight :weightList ){
                            weightListAdd.add(weight);
                        }
                        for (Medicine medicine :medList ){
                            medListAdd.add(medicine);
                        }
                        Profile profile = new Profile(
                                firstName
                                , lastName
                                , age
                                , height
                                , weightListAdd
                                , medListAdd);
                        profiles.add(profile);


                        //clear lists so info can be added to it again
                        weightList.clear();
                        medList.clear();
                    }
                    case "firstName": {
                        firstName = (String) parts[1];
                        break;
                    }
                    case "lastName": {
                        lastName = (String) parts[1];
                        break;
                    }
                    case "age": {
                        age = Integer.parseInt((String) parts[1]);
                        break;
                    }
                    case "height": {
                        height = Double.parseDouble((String) parts[1]);
                        break;
                    }

                    case "weight": {
                        String partOf = (String) parts[1];
                        //remove brackets
                        partOf = partOf.replace("[", "");
                        partOf = partOf.replace("]", "");
                        String[] info = partOf.split(", ");

                        for (String weightAndDate : info) {
                            String[] weightAndDateList = weightAndDate.split(" ");
                            //date
                            String date = weightAndDateList[0];
                            String[] dateList = date.split("-");
                            try {
                                int year = Integer.parseInt(dateList[0]);
                                int month = Integer.parseInt(dateList[1]);
                                int day = Integer.parseInt(dateList[2]);

                                //weight
                                double weightInKg = Double.parseDouble(weightAndDateList[1]);

                                //create weight and add to list
                                Weight weight = new Weight(day, month, year, weightInKg);
                                weightList.add(weight);
                            }catch (Exception e){ // can crash when the list is empty

                            }
                        }
                        break;
                    }
                    case "medicine":{


                        String partOf = (String) parts[1];
                        //remove brackets
                        partOf = partOf.replace("[", "");
                        partOf = partOf.replace("]", "");
                        String[] medicineList = partOf.split("-,");
                        for (String medicine : medicineList){
                            String[] medicineInfoList = medicine.split("-/");
                            //can crash when list is empty
                            try {
                                String medName = medicineInfoList[0];
                                String medDesc = medicineInfoList[1];
                                Double medDosis = Double.parseDouble(medicineInfoList[2].replace("-", ""));

                                //create new medicine object
                                Medicine medicineObject = new Medicine(medName, medDesc);
                                medicineObject.setDosis(medDosis);
                                medList.add(medicineObject);
                            } catch (Exception e) {
                            }





                        }

                    }




                }


            }

            myReader.close();
            Profile profile1 = profiles.get(0);

            return profiles;


        }catch(Exception e){
        }



        return null;
    }

    public void saveMedicine(List<Medicine> medList){
        try {
            FileWriter writer = new FileWriter("medicine.data");

            for (Medicine medicine : medList){
                String medName = medicine.getName();
                String medDesc = medicine.getDescription();
                writer.write((medName+"-/"+medDesc)+ System.lineSeparator());
            }
            writer.close();




        }catch (Exception e){

        }



    }

    public List<Medicine> loadMedicine(){
        List<Medicine> medList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("medicine.data"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Object[] parts = line.split("-/");

                String medName = (String) parts[0];
                String medDesc = (String) parts[1];
                Medicine medicine = new Medicine(medName,medDesc);
                medList.add(medicine);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Medicine medicine = medList.get(1);
        System.out.print(medicine.getName());
        return medList;



    }

}
