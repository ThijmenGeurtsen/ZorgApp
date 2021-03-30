import javax.print.attribute.standard.JobMediaSheetsCompleted;
import java.util.List;

public class ZorgApp {

    public static void main(String[] args) {
        Menu menu = new Menu();
        MakeLists listInfo = new MakeLists();
        List<Medicine> medList = listInfo.listMedicine();



        DataHandler data = new DataHandler();
        List<Profile> profiles = data.loadPatients();



        Language lan = new Language();
        lan.updateLanguage();






        while (true) {

            int selected = menu.loginAsEmployeePatient();
            if (selected == 0) {
                data.savePatients(profiles);
                break;
            }
            if (selected == 1) {
                menu.patientLogin(profiles);
            }
            if (selected == 2) {
                menu.employeeLogin(profiles);
            }
        }

    }
}

