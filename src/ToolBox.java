import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolBox {
    private Scanner scanner = new Scanner(System.in);
        public void clearScreen() {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void promptEnterKey(){
        Language lan = new Language();
        String language = lan.getLanguage();
        String pressEnter = language.equals("EN") ? "Press \"ENTER\" to continue..." : "Voer \"ENTER\" in om verder te gaan...";

        System.out.println(pressEnter);;
        scanner.nextLine();
    }

    public void line(){
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────");
    }

    public List<String> listPatients(List<Profile> patients) {

        List<String> names = new ArrayList<>();
        for (int i=0; i<patients.size();i++){
            Profile patient = patients.get(i);
            names.add(patient.getFirstAndLastName());


        }
        return names;
    }
}
