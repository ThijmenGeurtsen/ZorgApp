import java.lang.reflect.Array;
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
    private final MakeLists listInfo = new MakeLists();
    private final List<Medicine> meds = listInfo.listMedicine();
    ////////////////
    //  EditInfo //
    ///////////////
    private final Other other = new Other();

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
            List<Weight> weight = getWeight();
            Weight recentWeight = weight.get(weight.size() - 1);
            other.clearScreen();
            other.line();
            System.out.println("Press 0 to go back\n");
            System.out.println("Firstname: " + getFirstName());
            System.out.println("Lastname: " + getLastName());
            System.out.println("Age: " + getAge());

            System.out.println("Height: " + getHeight() + "cm");
            System.out.println("BMI: " + getBMI());


            System.out.println("Press 1 for more weight info" +
                    "\n\tCurrent weight : " + recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
            System.out.println("Press 2 for medicine info");


            other.line();
            System.out.println("Enter number:");
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
        other.line();
        for (int i = 0; i < meds.size(); i++) {
            Medicine medSel = meds.get(i);
            String desc = medSel.getDescription();
            String type = medSel.getType();
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

    public void editInfoMenu(List<String> options) {
        other.clearScreen();
        other.line();
        System.out.println("Press 0 to go back \n");

        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);

            //print out menu from list options
            switch (option) {
                case "firstName":
                    System.out.println("Press " + (i + 1) + " to edit " + "firstName: " + getFirstName());
                    break;

                case "lastName":
                    System.out.println("Press " + (i + 1) + " to edit " + "lastName: " + getLastName());
                    break;

                case "age":
                    System.out.println("Press " + (i + 1) + " to edit " + "age: " + getAge());
                    break;

                case "height":
                    System.out.println("Press " + (i + 1) + " to edit " + "height: " + getHeight() + "cm");
                    break;

                case "BMI":
                    System.out.println("Press " + (i + 1) + " to edit " + "BMI: " + getBMI());
                    break;

                case "weight":
                    List<Weight> weight = getWeight();
                    Weight recentWeight = weight.get(weight.size() - 1);
                    System.out.println("Press " + (i + 1) + " to edit " + "weight, newest weight: " + recentWeight.getDate() + " " + recentWeight.getWeight() + "kg");
                    break;

                case "medicine":
                    System.out.println("Press " + (i + 1) + " to edit " + "medicine");
                    break;

            }

        }
        other.line();
        System.out.println("Enter number:");
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
        other.clearScreen();
        other.line();
        System.out.println("Enter new firstName: ");
        String userInput = scanner.nextLine();
        setFirstName(userInput);

    }

    private void editLastName() {
        other.clearScreen();
        other.line();
        System.out.println("Enter new lastName: ");
        String userInput = scanner.nextLine();
        setLastName(userInput);

    }

    private void editAge() {
        while (true) {
            other.clearScreen();
            other.line();
            System.out.println("Enter new age: ");
            String userInput = scanner.nextLine();
            try {
                int userInputInt = Integer.parseInt(userInput);
                setAge(userInputInt);
                break;
            } catch (Exception e) {
            }

        }

    }

    private void editHeight() {
        while (true) {
            other.clearScreen();
            other.line();
            System.out.println("Enter new height: ");
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
            System.out.println("Enter new BMI: ");
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
        while (true) {
            other.clearScreen();
            other.line();
            displayWeight();
            System.out.println();
            System.out.println("press 0 to go back");
            System.out.println("Press 1 to add a new weight");
            other.line();
            System.out.println("Enter number:");
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

                if (userInputInt < (meds.size() + 1)) {
                    while (true) {
                        other.clearScreen();
                        other.line();
                        int index = userInputInt - 1;
                        Medicine selMedicine = meds.get(index);
                        System.out.println("Selected medicine " + selMedicine.getType() + " " + selMedicine.getDosis() + "mg");
                        System.out.println("Press 0 to go back");
                        System.out.println("Press 1 to edit the dosis");
                        System.out.println("Press 2 to delete the medicine");
                        other.line();
                        System.out.println("Enter number:");
                        String input = scanner.nextLine();

                        if (input.equals("0")) {
                            break;
                        }

                        //set new dosis
                        if (input.equals("1")) {
                            while (true) {
                                System.out.println("Enter new dosis:");
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


                    //add new medicine
                } else if (userInputInt == (meds.size() + 1)) {
                    Medicine outputMedicine = selectMedicine();
                    meds.add(outputMedicine);
                    setMedicine(meds);


                }


            } catch (Exception e) {
            }
        }
    }

    public void menuMedicine() {
        List<Medicine> meds = getMedicine();

        //print out pretty menu
        other.clearScreen();
        other.line();
        System.out.println("Press 0 to go back");
        System.out.println();
        for (int i = 0; i < meds.size(); i++) {
            Medicine medSel = meds.get(i);
            String desc = medSel.getDescription();
            String type = medSel.getType();
            double dosis = medSel.getDosis();

            System.out.println("press " + (i + 1) + " to edit " + type + " " + dosis + "mg");

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
        System.out.println("Press " + (meds.size() + 1) + " to add a new medicine");
        other.line();
    }

    //select medicine
    public Medicine selectMedicine() {
        while (true) {
            other.clearScreen();
            other.line();
            for (int i = 0; i < meds.size(); i++) {
                Medicine selMedicine = meds.get(i);
                System.out.println("Press " + i + " to add medicine " + selMedicine.getType());


            }
            other.line();
            System.out.println("Enter number");
            String userInput = scanner.nextLine();
            try {
                int userInputInt = Integer.parseInt(userInput);
                Medicine output = meds.get(userInputInt);
                while (true) {
                    System.out.println("Set dosis for " + output.getType() + ":");
                    String userInputDosis = scanner.nextLine();
                    double userInputDosisDouble = Integer.parseInt(userInputDosis);
                    output.setDosis(userInputDosisDouble);
                    return output;


                }


            } catch (Exception e) {
            }


        }


    }


}

