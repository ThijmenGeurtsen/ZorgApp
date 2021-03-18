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


    Profile(String firstName, String lastName, int age, double height, double BMI, List<Weight> weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.BMI = BMI;
        this.weight = weight;
        this.medicine = medicine;
    }


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
    public void getMedicine(Object i) {
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
                //medicine(profile);
            }
        }

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


    ////////////////
    //  EditInfo //
    ///////////////
    private Other other = new Other();
    private Scanner scanner = new Scanner(System.in);

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
        while(true) {
            other.clearScreen();
            other.line();
            displayWeight();
            System.out.println();
            System.out.println("press 0 to go back");
            System.out.println("Press 1 to add a new weight");
            other.line();
            System.out.println("Enter number:");
            String userInput = scanner.nextLine();  // Read user input

            if (userInput.equals("0")){
                break;
            }
            if (userInput.equals("1")){
                //Weight weight = createWeight();
                Weight weight = new Weight(13,3,2020,80);
                List<Weight> weights = getWeight();
                weights.add(weight);
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
        Weight output = new Weight(day,month,year,weight);
        return output;
    }

}

