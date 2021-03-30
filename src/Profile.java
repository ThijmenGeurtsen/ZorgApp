import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Profile {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    private double BMI;
    private List<Weight> weight = new ArrayList<>();
    private List<Medicine> medicine = new ArrayList<>();

    ///medicine
    private DataHandler data = new DataHandler();
    private List<Medicine> medicineList = data.loadMedicine();


    private Language lan = new Language(); // language
    ////////////////
    //  EditInfo //
    ///////////////
    private ToolBox other = new ToolBox();

    public String getFirstAndLastName() {
        return (firstName + " " + lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getBMI() {
        return BMI;
    }

    public List<Weight> getWeight() {
        return weight;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setFirstName(String i) {
        firstName = i;
    }

    public void setLastName(String i) {
        lastName = i;
    }

    public void setAge(int i) {
        age = i;
    }

    public void setHeight(double i) {
        height = i;
    }

    public void setBMI(double i) {
        BMI = i;
    }

    public void setWeight(List<Weight> i) {
        weight = i;
    }

    private final Scanner scanner = new Scanner(System.in);


    Profile(String firstName, String lastName, int age, double height, List<Weight> weight, List<Medicine> medicine) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.BMI = BMI;
        this.weight = weight;
        this.medicine = medicine;
    }

    public void displayWeight() {
        List<Weight> weights = getWeight();
        for (int i = 0; i < weights.size(); i++) {
            Weight weightProfile = weights.get(i);      //get list of weights
            double weight = weightProfile.getWeight();  //get weight
            String date = weightProfile.getDate();      // get date

            int loop = (int) (weight / 5);

            if (loop > 60) {
                loop = 60;
            } //set loop to 60 if over 60

            //print out enters
            if (i != 0) {
                System.out.println();
            }

            //print out menu

            System.out.print("[" + (date) + "]" + weight + "kg \t");
            IntStream.range(1, loop).forEach(s -> System.out.print("█")); // print out bar

        }
        System.out.println();

    }

    public void setMedicine(Object i) {
        i = medicine;
    }


    
    /////////////////
    // DisplayInfo //
    /////////////////
    public void displayInfo() {

        while (true) {

            String language = lan.getLanguage();

            List<Weight> weight = getWeight();
            Weight recentWeight = weight.get(weight.size() - 1);
            other.clearScreen();
            other.line();
            String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
            System.out.println(goBack+"\n");
            String firstName = language.equals("EN") ? "Firstname: " : "Voornaam: ";
            String lastName = language.equals("EN") ? "Lastname: " : "Achternaam: ";
            String age = language.equals("EN") ? "Age: " : "Leeftijd: ";
            String height = language.equals("EN") ? "Height: " : "Hoogte: ";
            System.out.println(firstName+ getFirstName() );
            System.out.println(lastName+ getLastName() );
            System.out.println(age + getAge());

            System.out.println(height+ getHeight() + "cm");
            System.out.println("BMI: " + getBMI());

            if (language.equals("NE")) {
                System.out.println("Voer 1 in om alle gewichten te zien" +
                        "\n\tMeest recente gewicht: " + recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
            } else if (language.equals("EN")){
                System.out.println("Press 1 for more weight info" +
                        "\n\tCurrent weight : " + recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
            }
            String medsInfo = language.equals("EN") ? "Press 2 for medicine info" : "Voer 2 in voor medicijn informatie";
            System.out.println(medsInfo);


            other.line();
            String enterNumber = language.equals("EN") ? "Enter Number:" : "Voer het nummer in:" ;
            System.out.println(enterNumber);
            String userInput = scanner.nextLine();  // Read user input
            if (userInput.equals("0")) {
                break;
            }
            if (userInput.equals("1")) {
                other.clearScreen();
                other.line();
                displayWeight();
                other.line();
                other.promptEnterKey();
            }
            if (userInput.equals("2")) {
                displayMedicine();
            }
        }

    }

    public void displayMedicine() {
        List<Medicine> meds = getMedicine();

        //print out pretty menu
        other.clearScreen();
        other.line();
        for (int i = 0; i < meds.size(); i++) {
            Medicine medSel = meds.get(i);
            String desc = medSel.getDescription();
            String type = medSel.getName();
            double dosis = medSel.getDosis();

            System.out.println("[" + (i + 1) + "] " + type + " " + dosis + "mg");

            //print description
            String[] descLines = desc.split("(?<=\\G.{1})");
            List<String> textList = new ArrayList<>();
            int loop = 0;
            for (String character : descLines) {
                loop = loop + 1;
                System.out.print(character);
                if (loop > 80 && character.equals(" ")) {
                    System.out.println();
                    loop = 0;

                }

            }
            System.out.println("\n");


        }
        other.line();
        other.promptEnterKey();


    }

    ///////////////
    // EditInfo //
    //////////////
    public void editInfoMenu(List<String> options) {
        other.clearScreen();
        other.line();
        String language = lan.getLanguage();

        String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
        System.out.println(goBack+"\n");

        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);

            //print out menu from list options
            switch (option) {
                case "firstName": {
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "firstName: " + getFirstName());
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de voornaam te bewerken: " + getFirstName());
                    }

                    break;
                }

                case "lastName":
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "lastName: " + getLastName());
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de achernaam te bewerken: " + getLastName());
                    }
                    break;

                case "age":
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "age: " + getAge());
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de leeftijd te bewerken: " + getAge());
                    }
                    break;

                case "height":
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "height: " + getHeight() + "cm");
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de hoogte te bewerken: " + getHeight());
                    }
                    break;

                case "BMI":
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "BMI: " + getBMI());
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de BMI te bewerken: " + getBMI());
                    }

                    break;

                case "weight":
                    List<Weight> weight = getWeight();
                    Weight recentWeight = weight.get(weight.size() - 1);

                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "weight, newest weight: " +
                                recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om het gewicht te bewerken" + ", nieuwste gewicht: " +
                                recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
                    }

                    break;

                case "medicine":
                    if (language.equals("EN")) {
                        System.out.println("Press " + (i + 1) + " to edit " + "medicine");
                    } else if (language.equals("NE")){
                        System.out.println("Voer " + (i + 1) + " in om de medicijnen te bewerken");
                    }

                    break;

            }

        }
        other.line();
        String enterNumber = language.equals("EN") ? "Enter Number:" : "Voer het nummer in:" ;
        System.out.print(enterNumber);
        String userInput = scanner.nextLine();
        try {
            int userInputInt = Integer.parseInt(userInput);
            String selOption = options.get(userInputInt - 1);
            editTypes(selOption);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editTypes(String option) {
        switch (option) {
            case "firstName":
                editFirstName();
                break;

            case "lastName":
                editLastName();
                break;

            case "age":
                editAge();
                break;

            case "height":
                editHeight();
                break;

            case "BMI":
                editBMI();
                break;

            case "weight":
                menuWeights();
                break;

            case "medicine":
                editMedicine();
                break;


        }
    }

    private void editFirstName() {
        String language = lan.getLanguage();
        other.clearScreen();
        other.line();
        String newName = language.equals("EN") ? "Enter new firstName: " : "Voer een nieuwe voornaam in: ";
        System.out.println(newName);
        String userInput = scanner.nextLine();
        setFirstName(userInput);

    }

    private void editLastName() {
        other.clearScreen();
        other.line();
        String language = lan.getLanguage();
        String newLastName = language.equals("EN") ? "Enter new firstName: " : "Voer een nieuwe achternaam in: ";
        System.out.print(newLastName);
        String userInput = scanner.nextLine();
        setLastName(userInput);

    }

    private void editAge() {
        while (true) {
            String language = lan.getLanguage();
            other.clearScreen();
            other.line();
            String newAge = language.equals("EN") ? "Enter new age: " : "Voer een nieuwe leeftijd in: ";
            System.out.println(newAge);
            String userInput = scanner.nextLine();
            try {
                int userInputInt = Integer.parseInt(userInput);
                setAge(userInputInt);
                break;
            } catch (Exception e) {
            }

        }

    }

    private void editHeight(){
        while (true) {
            String language = lan.getLanguage();
            other.clearScreen();
            other.line();
            String newHeight = language.equals("EN") ? "Enter new height: " : "Voer een nieuwe hoogte in: ";
            System.out.print(newHeight);
            String userInput = scanner.nextLine();
            try {
                double userInputDouble = Integer.parseInt(userInput);
                setHeight(userInputDouble);
                break;
            } catch (Exception e) {
            }

        }

    }

    private void editBMI() {
        while (true) {
            other.clearScreen();
            other.line();
            String language = lan.getLanguage();
            String newBMI = language.equals("EN") ? "Enter new BMI: " : "Voer een nieuwe BMI in: ";
            System.out.println(newBMI);

            String userInput = scanner.nextLine();
            try {
                double userInputDouble = Integer.parseInt(userInput);
                setBMI(userInputDouble);
                break;
            } catch (Exception e) {
            }

        }

    }

    private void menuWeights() {
        String language = lan.getLanguage();
        while (true) {
            other.clearScreen();
            other.line();
            displayWeight();
            System.out.println();
            String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
            System.out.println(goBack);
            other.line();
            String enterNumber = language.equals("EN") ? "Enter Number:" : "Voer het nummer in:" ;
            System.out.println(enterNumber);
            String userInput = scanner.nextLine();  // Read user input

            if (userInput.equals("0")) {
                break;
            }
            if (userInput.equals("1")) {
                Weight weight = createWeight();
                List<Weight> weights = getWeight();
                weights.add(weight);
                other.promptEnterKey();

                setWeight(weights);

            }
        }

    }

    private Weight createWeight() {
        int year;
        int month;
        int day;
        double weight;
        while (true) {
            try {
                System.out.println("enter year:");
                String yearString = scanner.nextLine();
                year = Integer.parseInt(yearString);
                break;
            } catch (Exception e) {
            }
        }
        while (true) {
            try {
                System.out.println("enter month:");
                String stringMonth = scanner.nextLine();
                month = Integer.parseInt(stringMonth);
                break;
            } catch (Exception e) {
            }
        }
        while (true) {
            try {
                System.out.println("enter day:");
                String stringDay = scanner.nextLine();
                day = Integer.parseInt(stringDay);
                break;
            } catch (Exception e) {
            }
        }


        while (true) {
            try {
                System.out.println("enter weight:");
                String stringWeight = scanner.nextLine();
                weight = Integer.parseInt(stringWeight);
                break;
            } catch (Exception e) {
            }
        }
        Weight output = new Weight(day, month, year, weight);
        return output;
    }

    //edit medicine
    public void editMedicine() {
        String language = lan.getLanguage();
        while (true) {
            List<Medicine> meds = getMedicine();
            menuMedicine(); // print out menu medicine
            String userInput = scanner.nextLine();

            try {

                int userInputInt = Integer.parseInt(userInput);
                //edit selected medicine
                if (userInputInt == 0) {
                    break;
                }

                int index = userInputInt - 1;
                if (userInputInt < (meds.size() + 1)) {

                    editSelectedMedicine(index, meds);


                    //add new medicine
                }
                if (index == (meds.size())) {
                    Medicine outputMedicine = selectMedicine();
                    System.out.print(outputMedicine);
                    meds.add(outputMedicine);
                    setMedicine(meds);


                }


            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    public void editSelectedMedicine(int index,  List<Medicine> meds) {
        String language = lan.getLanguage();
        while (true) {
            other.clearScreen();
            other.line();
            Medicine selMedicine = meds.get(index);
            String selectedMedicine = language.equals("EN") ? "Selected medicine " : "geselecteerde medicijn ";
            System.out.println(selectedMedicine + selMedicine.getName() + " " + selMedicine.getDosis() + "mg");

            String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
            System.out.println(goBack);

            String editDosis = language.equals("EN") ? "Press 1 to edit the dosis" : "Voer 1 om de dosis te bewerken";
            System.out.println(editDosis);

            String deleteMedicine = language.equals("EN") ? "Press 2 to delete the medicine" : "Voer 2 in om het medicijn te verwijderen";
            System.out.println(deleteMedicine);

            other.line();
            String enterNumber = language.equals("EN") ? "Enter Number:" : "Voer het nummer in:" ;
            System.out.print(enterNumber);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            //set new dosis
            if (input.equals("1")) {

                while (true) {
                    String enterDosis = language.equals("EN") ? "Enter new dosis:" : "Voer een nieuwe dosis in:" ;
                    System.out.println(enterDosis);
                    String newDosis = scanner.nextLine(); // get userinput newdosis
                    try {
                        //set new dosis and return
                        double newDosisDouble = Double.parseDouble(newDosis);
                        selMedicine.setDosis(newDosisDouble);
                        meds.set(index, selMedicine);
                        setMedicine(meds);
                        break;


                    } catch (Exception e) {
                    }
                }
                break;
            }

            if (input.equals("2")) {
                meds.remove(index);
                break;
            }
        }

    }

    public void menuMedicine() {
        List<Medicine> meds = getMedicine();
        String language = lan.getLanguage();

        //print out pretty menu
        other.clearScreen();
        other.line();
        String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
        System.out.print(goBack);

        System.out.println();
        for (int i = 0; i < meds.size(); i++) {
            Medicine medSel = meds.get(i);
            String desc = medSel.getDescription();
            String type = medSel.getName();
            double dosis = medSel.getDosis();

            if (language.equals("EN")) {
                System.out.println("Press " + (i + 1) + " to edit " + type + " " + dosis + "mg");
            } else if(language.equals("NE")){
                System.out.println("Voer " + (i + 1) + " in om " + type + " " + dosis + "mg"+" te bewerken");
            }

            //print description
            String[] descLines = desc.split("(?<=\\G.{1})");
            List<String> textList = new ArrayList<>();
            int loop = 0;
            for (String character : descLines) {
                loop = loop + 1;
                System.out.print(character);
                if (loop > 80 && character.equals(" ")) {
                    System.out.println();
                    loop = 0;
                }

            }
            System.out.println("\n");
        }
        if (language.equals("EN")) {
            System.out.println("Press " + (meds.size() + 1) + " to add a new medicine");
        } else if(language.equals("NE")){
            System.out.println("Druk " + (meds.size() + 1) + " om een nieuw medicijn toe te voegen");
        }
        other.line();
    }

    //select medicine
    public Medicine selectMedicine() {
        String language = lan.getLanguage();
        while (true) {
            other.clearScreen();
            other.line();
            String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
            System.out.println(goBack);
            for (int i = 0; i < medicineList.size(); i++) {
                Medicine selMedicine = medicineList.get(i);
                if (language.equals("EN")) {
                    System.out.println("Press " + (1+1) + " to add medicine " + selMedicine.getName());
                } else if (language.equals("NE")){
                    System.out.println("Voer " + (i+1)+ " in om medicijn " + selMedicine.getName()+" toe te voegen ");

                }



            }
            other.line();
            String enterNumber = language.equals("EN") ? "Enter Number:" : "Voer het nummer in:" ;
            System.out.println(enterNumber);
            String userInput = scanner.nextLine();
            if (userInput.equals("0")){ break;}
            try {
                int userInputInt = (Integer.parseInt(userInput)-1);
                Medicine output = medicineList.get(userInputInt);
                while (true) {
                    if (language.equals("EN")) {
                        System.out.println("Set dosis for " + output.getName() + ":");
                    } else if(language.equals("NE")){
                        System.out.println("Zet de dosering voor " + output.getName() + ":");
                    }
                    String userInputDosis = scanner.nextLine();
                    double userInputDosisDouble = Integer.parseInt(userInputDosis);
                    output.setDosis(userInputDosisDouble);
                    return output;


                }


            } catch (Exception e) {
            }


        }


        return null;
    }


}

