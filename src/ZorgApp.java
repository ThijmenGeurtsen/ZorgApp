import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ZorgApp {

    public static void main(String[] args) {
        Menu menu = new Menu();
        MakeLists listInfo = new MakeLists();

        List<Profile> profiles = listInfo.listPatients();

        DataHandler data = new DataHandler();
        data.save(profiles);
        data.load();


        while (true) {
            Language lan = new Language();
            lan.updateLanguage();
            while (true) {

                int selected = menu.loginAsEmployeePatient();
                if (selected == 0) {
                    menu.patientLogin(profiles);
                }
                if (selected == 1) {
                    menu.employeeLogin(profiles);
                }
            }
        }
    }
}

