import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Menu {
    public Other other = new Other();
    private Language lan = new Language();

    private final Scanner scanner = new Scanner(System.in);

    //////////////////////////////
    //other
    //////////////////////////////
    public int loginAsEmployeePatient() {
        int loop = 0;
        while (loop == 0) {

            other.clearScreen();
            other.line();

            String language = lan.getLanguage();
            //login as
            String loginAs = language.equals("NE") ? "Hoe wilt u inloggen?" : "as what would you like to login?";
            System.out.println(loginAs);


            System.out.println();
            Scanner option = new Scanner(System.in);  // Create a Scanner object
            System.out.println("type 0 to login as a patient");
            System.out  .println("type 1 to login as a hospital employee");
            other.line();

            //get user input
            System.out.println("Enter number:");
            String enteredOption = option.nextLine();

            //check user input
            if (Arrays.asList("0", "1").contains(enteredOption)) {
                int output = Integer.parseInt(enteredOption);
                return output;
            }
        }

        return 0;
    }

    private int editOrDisplay() {
        other.clearScreen();
        other.line();
        System.out.println("Press 0 to go back");
        System.out.println();
        System.out.println("Press 1 to display info patient");
        System.out.println("Press 2 to edit info patient");
        other.line();
        System.out.println("Enter number:");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (Exception e) {
        }
        return -1;
    }



    //////////////////////////////////////
    ///////Employee
    //////////////////////////////////////
    public void employeeLogin(List<Profile> profiles) {

        while (true) {
            int index = selectProfileEmployee(profiles);
            if (index == -1) {
                break;
            }
            if (index != -2) {
                while (true) {
                    Profile profile = profiles.get(index);
                    int editDisplay = editOrDisplay();
                    if (editDisplay == 0){break;}
                    if (editDisplay == 1) {profile.displayInfo();}
                    if (editDisplay == 2) {profile.editInfoMenu(Arrays.asList("firstName", "lastName", "age", "height", "BMI", "weight", "medicine"));}


                }
                // get profile
            }

        }
    }

    private int selectProfileEmployee(List<Profile> profiles) {

        List<String> names = other.listPatients(profiles); //generate list of names
        List<String> listFirstThreeChar = listFirstThreeChar(names);// list of first three characters of the names


        other.clearScreen();
        other.line();


        displayPatientsList(names); //display patients

        //get user input
        System.out.println();
        System.out.println("Press 0 to go back.");
        other.line();
        System.out.println("Enter first three letter of the first name:");
        String userInput = scanner.nextLine();


        if (userInput.equals("0")) {
            return -1;
        }


        List<Integer> listOfSameNames = compareInputToList(listFirstThreeChar, userInput);
        int output = selectName(listOfSameNames, names); // select name out of options given

        if (output != -1) {
            return output;
        }


        return -2;
    }

    private int selectName(List<Integer> indexNames, List<String> names) { // print out list of index given
        int userInputInt = -1;
        while (true) {
            // print out list of options
            other.clearScreen();
            other.line();
            System.out.println("press 0 to go back");
            System.out.println();
            for (int i = 0; i < indexNames.size(); i++) {
                String name = names.get(indexNames.get(i));
                System.out.println("press " + (i + 1) + " for " + name);
            }
            other.line();
            String userInput = scanner.nextLine(); // get user input
            if (userInput.equals("0")) {
                break;
            }
            //convert userInput to int
            try {
                userInputInt = Integer.parseInt(userInput);
                userInputInt = indexNames.get((userInputInt - 1)); // get selected index
                break;


            } catch (Exception e) {

            }

        }

        return userInputInt;
    }

    private List<Integer> compareInputToList(List<String> list, String input) {
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);

            if (input.equals(name)) {
                index.add(i);
            }


        }

        return index;
    }

    private List<String> listFirstThreeChar(List<String> names) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i); // get name
            String firstThreeChar = name.substring(0, 3); // select first three chars
            list.add(firstThreeChar);

        }

        return list;
    }

    private void displayPatientsList(List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));

        }
    }


    //////////////////////////////////////
    //patient
    /////////////////////////////////////
    public void patientLogin(List<Profile> profiles) {
        while (true) {

            int index = selectProfilePatient(profiles);

            if (index == -1) {
                break;
            }
            Profile profile = profiles.get(index);
            while (true) {
                int editDisplay = editOrDisplay();
                if (editDisplay == 0){break;}
                if (editDisplay == 1) {profile.displayInfo();}
                if (editDisplay == 2) {profile.editInfoMenu(Arrays.asList("firstName"));}


            }
            // get profile


        }
    }

    public int selectProfilePatient(List<Profile> profiles) {
        List<String> names = other.listPatients(profiles);

        other.clearScreen();
        other.line();
        System.out.println("Press 0 to go back");
        System.out.println();
        System.out.println("Enter firstname and last name:");
        other.line();
        String nameInput = scanner.nextLine();  // Read user input

        if (nameInput.equals("0")) {
            return -1;
        }

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (name.equals(nameInput)) {
                return i;


            }


        }
        return -1;
    }


}



