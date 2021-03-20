import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Other {
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
        System.out.println("Press \"ENTER\" to continue...");
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
