import java.util.Scanner;

public class Language {
    private static Menu menu = new Menu();
    private static String language;


    public String getLanguage(){
        return language;
    }

    public void updateLanguage(){
        while(true) {
            Other other = new Other();
            other.clearScreen();
            other.line();
            System.out.println("Press 0 to select english");
            System.out.println("Voer 1 in voor nederlands");
            other.line();
            System.out.println("Enter number:");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("0")) {
                language = "EN";
                break;

            } else if (userInput.equals("1")) {
                language = "NE";
                break;

            }

        }
    }
}
