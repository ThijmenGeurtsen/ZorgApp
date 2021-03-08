import org.json.simple.JSONObject;

import java.util.*;


public class Menu {
    private int loop = 0;
    public SmallFunctions small_func = new SmallFunctions();
    public JSON json = new JSON();
    private Scanner scannerObject = new Scanner(System.in);  // Create a Scanner
    private List<String> infoTypes = Arrays.asList("firstName","lastName","length","weight","age","BMI","medicine");
    public EditType Edit = new EditType();
    public DisplayTypes Display = new DisplayTypes();


    //////////////////////////////////////
    //patient main
    ///////////////////////
    public void pMain() {
        while (true) {
            int index = pSelectProfile();
            if (index == -1){
                break;
            }
            while(true) {
                String option = optionDisplayEdit();
                if (option.equals("0")){
                    break;
                }
                if (option.equals("1")) {
                    small_func.clearScreen();
                    displayPatientInfo(index);
                    small_func.promptEnterKey();

                }
                if (option.equals("2")) {
                    Edit.editMain(index, Arrays.asList("firstName"));
                }
            }
        }
    }
    //////////////////////
    //patient selectProfile
    /////////////////////
    public int pSelectProfile() {
        /////////////////////
        ///select profile
        ////////////////////
        int loop = 0;
        while (loop == 0) {
            //get user input
            Scanner enter_name = new Scanner(System.in);  // Create a Scanner object
            small_func.clearScreen();
            small_func.line();
            System.out.println("Press 0 to go back");
            System.out.println();
            System.out.println("Enter firstname and last name:");
            String name = enter_name.nextLine();  // Read user input

            if (name.equals("0")){ //makes it break in the main function
                return -1;
            }
            //if name exists return index
            if (json.check_name(name) == true) {
                int selected = json.name_index(name);
                return selected;
            }
            System.out.println("that name was incorrect please try again.");
            //change later to press enter to continue
            small_func.sleep(2000);
            small_func.clearScreen();

        }
        return -1;
    }

    public void main(){
        while(true) {
            int index = selectProfile(Boolean.TRUE);
            if (index == -1){
                break;
            }
            while (true) {
                String option = optionDisplayEdit();
                if (option.equals("0")){
                    break;
                }
                if (option.equals("1")) {
                    small_func.clearScreen();
                    displayPatientInfo(index);
                    small_func.promptEnterKey();

                }
                if (option.equals("2")) {
                    Edit.editMain(index, Arrays.asList("firstName", "lastName", "length", "weight", "age", "BMI", "medication"));
                }
            }
        }

    }

    public class EditType {
        public void displayList(int index, List<String> allowedToEdit){
            for (int i = 0; i < allowedToEdit.size(); i++) {
                //print out different statement for medication
                if (allowedToEdit.get(i).equals("medication")) {
                    System.out.println("Press " + (i + 1) + " to edit "
                            + allowedToEdit.get(i)
                            + ": " +
                            json.getStringList(index,"medicine"));

                } else {
                    System.out.println("Press " + (i + 1) + " to edit "
                            + allowedToEdit.get(i)
                            + ": " +
                            json.get_string_i(index, allowedToEdit.get(i)));

                }

            }
        }
        public void editString(int index, List<String> allowedToEdit, int intInput){
            //edit selected variable
            small_func.clearScreen();
            small_func.line();
            System.out.println("Current " + allowedToEdit.get(intInput - 1) + " is "
                    + json.get_string_i(index, allowedToEdit.get(intInput - 1)));

            //get user input
            System.out.println("What would you like to edit it to:");
            String editTo = scannerObject.nextLine();  // Read user input

            //update json file
            String edit = allowedToEdit.get(intInput - 1);
            json.updateVariable(index, edit, editTo);
        }
        public void editMain(int index, List<String> allowedToEdit) {
            while (true) {
                ////////////////////////////////
                //select option to edit
                //////////////////////////////
                small_func.clearScreen();
                small_func.line();
                System.out.println("Press 0 to go back.");
                System.out.println();

                //display what you can edit with numbers
                displayList(index,allowedToEdit);

                /////////////
                ///select number
                /////////////
                small_func.line();

                //get user input
                System.out.println("Enter number:");
                String userInput = scannerObject.nextLine();  // Read user input

                //check input and convert to int
                int intInput = 0;
                try {
                    intInput = Integer.parseInt(userInput);
                    //break if needed
                    if (intInput == 0) {
                        break;
                    }
                    //check if should go back
                    System.out.println(allowedToEdit.get(intInput - 1));
                    if (allowedToEdit.get(intInput - 1) == "medication") {
                        medicine(index);
                        break;

                    }


                    //run code here to edit
                    if (intInput <= infoTypes.size()) {
                        editString(index,allowedToEdit, intInput);

                    }
                } catch (Exception e) {
                }


            }


        }
    }
    public String optionDisplayEdit(){
        while(loop==0) {
            small_func.clearScreen();
            small_func.line();
            System.out.println("Press 0 to go back.");
            System.out.println("Press 1 to display info");
            System.out.println("Press 2 to edit info back");

            small_func.line();

            //get user input
            System.out.println("Enter number:");
            String sel = scannerObject.nextLine();  // Read user input

            if (sel.equals("0")){
                return sel;
            }
            //check userinput
            if (Arrays.asList("1", "2").contains(sel)){
                return sel;
            }
        }

        return null;
    }
    public void medicine(int index){
        System.out.println(json.getStringList(index,"medicine"));
        small_func.line();
        String userInput = scannerObject.nextLine();  // Read user input
        small_func.line();
        List<String> jsonArray = json.list_patients();





    }
    //makes you login
    public String loginAsEmployeePatient(){
        int loop = 0;
        while (loop == 0) {
            small_func.clearScreen();
            small_func.line();
            System.out.println("as what would you like to login?");
            System.out.println();
            Scanner option = new Scanner(System.in);  // Create a Scanner object
            System.out.println("type 0 to login as a patient");
            System.out.println("type 1 to login as a hospital employee");
            small_func.line();

            //get user input
            System.out.println("Enter number:");
            String entered_option = option.nextLine();

            //check user input
            if (Arrays.asList("0", "1").contains(entered_option)){
                small_func.clearScreen();
                return entered_option;
            }
            System.out.println("the given input was incorrect");
            //change later to press enter to continue
            small_func.sleep(2000);
            small_func.clearScreen();
        }


        return "not working";
    }
    ///////////////////////
    //print all patients
    ///////////////////////
    public void displayPatientInfo(int index) {
        System.out.println("FirstName: " + json.get_string_i(index, "firstName"));
        System.out.println("LastName: " + json.get_string_i(index, "lastName"));
        System.out.println("Length: " + json.get_string_i(index, "length"));
        System.out.println("Weight: " + json.get_string_i(index, "weight"));
        System.out.println("Age: " + json.get_string_i(index, "age"));
        System.out.println("BMI: " + json.get_string_i(index, "BMI"));
        System.out.println("Medication: " + json.getStringList(index,"medicine"));
        small_func.line();
    }

    public class EditTyped{
        public void medicine(){

        }
        public class weight{

            public void main(int index){

            }
            public int selectMedicine(int index){
                List<String> medicine = json.getStringList(index,"medicine");
                List<String> medDescription = json.getStringList(index,"medicineDescription");

                for(int i=0;i<medicine.size();i++){
                    if (i!=0) { System.out.println();}
                    System.out.println("Medicine "+i+": "+medicine.get(i));
                    System.out.println(medDescription.get(i));

                }
                return -1;
            }
        }
    }
    public class DisplayTypes{
        public void medicine(int index){
            //get all medicine info
            JSONObject medList = json.getJSONObject(0,"medsInfo");

            List<String> medicine = (List<String>) medList.get("medicine");
            List<String> medicineDescription = (List<String>) medList.get("medicineDescription");
            List<Integer> medicineWeight = (List<Integer>) medList.get("medicineWeight");


            for(int i=0;i<medicine.size();i++) {
                if (i != 0) {
                    System.out.println();
                }
                System.out.println("Medicine " + (i+1) + ": "+medicineWeight.get(i)+"mg \t" + medicine.get(i));
                System.out.println(medicineDescription.get(i));

            }
        }

        public void weight(int index){
            List<Integer> test = (json.getIntergerList(index,"weight"));
            System.out.println(test);
            //test.get(1);
            int index2 = 1;
            var test2 = test.get(index2);


            List<Integer> weights = Arrays.asList(50,60,70);

            for (int i=0;i<test.size();i++){
                int weight = weights.get(1);

                int loop = weight/10;

                //if last digit is bigger than 5: loop+1
                int lastDigit = weight % 10;
                if (lastDigit>=5) {loop = loop+1;}


                //set loop to 30 if ove 30
                if (loop >30){ loop = 30;}

                //print out menu
                if (i != 0){System.out.println();}
                System.out.print("["+(i+1)+"]"+weight+"kg \t");

                for (int s=0;s<(loop);s++){
                    System.out.print("*");

                }


            }



        }
    }

    //generates list of all patients
    public void displayPatients() {
        List<String> names = json.list_patients();
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
    }


    ///////////////////////////
    //let user select profile
    //////////////////////////
    //////////////////////////
    //first: true,false. True means it will display profiles.
    public int selectProfile(boolean test) {
        /////////////////////
        ///select profile
        ////////////////////

        while (loop == 0) {

            small_func.clearScreen();
            //display all patients

            if (test == true) {

                small_func.line();
                displayPatients();
                small_func.line();
                System.out.println("Press 0 to go back.");
                System.out.println("Search in database enter firstname and lastname");


            }
            if (test == false){
                System.out.println("Press 0 to go back.");
            }

            //get user input
            System.out.println("enter first name or last name");

            String name = scannerObject.nextLine();  // Read user input

            if (name.equals("0")){
                return -1;
            }
            //if name exists return index
            if (json.check_name(name) == true) {
                int selected = json.name_index(name);
                return selected;

                /////////////////////////////////////////
                ///if input is wrong select comparable name
                //////////////////////////////////////////
            } else{
                ClosestsString closeString = new ClosestsString();
                List<String> possibleNames = closeString.closestsMatch(name);
                while (loop == 0){
                    small_func.clearScreen();
                    System.out.println("The given name was wrong please select the correct profile.");
                    small_func.line();
                    System.out.println("Enter 0 to go back.");
                    for (int i=0; i<possibleNames.size();i++){
                        System.out.println("Press "+(i+1)+" to select "+possibleNames.get(i));
                    }
                    small_func.line();
                    System.out.println("Enter number to select profile");

                    String selectInput = scannerObject.nextLine();  // Read user input

                    ///convert
                    try{
                        int intInput=Integer.parseInt(selectInput);

                        if (intInput==0) {
                            break;
                        }
                        if (intInput<=possibleNames.size()){
                            //getName
                            String nameSel = possibleNames.get(intInput-1);
                            return json.name_index(nameSel);

                        }else{
                            System.out.println("not a profile");
                        }
                    } catch(Exception e){
                    }
                }
            }
            /////////////////////////////////////
            /////////////////////////////////////
            //change later to press enter to continue
            small_func.clearScreen();

        }


        return -1;
    }
}

