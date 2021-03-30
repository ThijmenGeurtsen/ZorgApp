import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Menu {
    public ToolBox other = new ToolBox();
    private Language lan = new Language();

    private final Scanner scanner = new Scanner(System.in);

    //////////////////////////////
    //other
    //////////////////////////////
    public int loginAsEmployeePatient() {
        while (true) {

            other.clearScreen();
            other.line();

            String language = lan.getLanguage();

            //login as
            String textLoginAs = language.equals("NE") ? "hoe wilt u inloggen?" : "as what would you like to login?";
            System.out.println(textLoginAs);
            System.out.println();
            Scanner option = new Scanner(System.in);  // Create a Scanner object

            String goBack = language.equals("NE") ? "Voer in 0 om af te sluiten en op te slaan" : "type 0 to exit and save";
            System.out.println(goBack);
            String textLoginPatient = language.equals("NE") ? "Voer in 1 om als patient in te loggen" : "type 1 to login as a patient";
            System.out.println(textLoginPatient);
            String textLoginEmployee = language.equals("NE") ? "Voer in 2 om als werknemer in te loggen" : "type 2 to login as a hospital employee";
            System.out.println(textLoginEmployee);
            other.line();

            //get user input
            String enterNumber = language.equals("NE") ? "Voer het nummer in:" : "Enter number:";
            System.out.println(enterNumber);
            String enteredOption = option.nextLine();

            //check user input
            if (Arrays.asList("0","1", "2").contains(enteredOption)) {
                int output = Integer.parseInt(enteredOption);
                return output;
            }
        }
    }

    private int editOrDisplay() {
        other.clearScreen();
        other.line();
        String language = lan.getLanguage();
        String goBack = language.equals("NE") ? "Voer 0 om terug te gaan" : "Press 0 to go back";
        System.out.println(goBack);

        System.out.println();
        String displayPatientInfo = language.equals("NE") ? "Voer 1 om patient info te laten" : "Press 1 to display info patient";
        System.out.println(displayPatientInfo);

        String displayEmployeeInfo = language.equals("NE") ? "Voer 2 om de patient te bewerken" : "Press 2 to edit info patient";
        System.out.println(displayEmployeeInfo);

        other.line();
        String enterNumber = language.equals("NE") ? "Voer een nummer in:" : "Enter number:";
        System.out.println(enterNumber);
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
        String language = lan.getLanguage();
        System.out.println();
        String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
        System.out.println(goBack);
        other.line();
        String firstThreeChars = language.equals("EN") ? "Enter first three letter of the first name:" : "Voer de eerste drie letters in van de voornaam";
        System.out.println(firstThreeChars);

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
        String language = lan.getLanguage();
        while (true) {
            // print out list of options
            other.clearScreen();
            other.line();
            String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
            System.out.println(goBack);
            System.out.println();
            for (int i = 0; i < indexNames.size(); i++) {
                String name = names.get(indexNames.get(i));
                if (language.equals("EN")) {
                    System.out.println("press " + (i + 1) + " for " + name);
                } else if (language.equals("NE")) {
                    System.out.println("Voer " + (i + 1) + " in voor " + name);
                }
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

        String language = lan.getLanguage();
        String goBack = language.equals("EN") ? "Press 0 to go back" : "Voer 0 in om terug te gaan";
        System.out.print(goBack);

        System.out.println();

        String enterFirstName = language.equals("EN") ? "Enter firstname and last name:" : "Voer de voornaam en achternaam in";
        System.out.println(enterFirstName);
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



