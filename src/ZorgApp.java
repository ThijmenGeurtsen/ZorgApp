import java.util.Arrays;
import java.util.List;

public class ZorgApp {
    public static void main(String[] args){

        MakeLists listInfo = new MakeLists();

        List<Profile> profiles = listInfo.listPatients();

        Profile profile1 = profiles.get(0);

        Menu menu = new Menu();
        int loop = 1;
        while(loop==1) {
            //profile1.editInfoMenu(Arrays.asList("firstName", "lastName", "age", "height", "BMI", "weight", "medicine"));
            break;
        }
        while(true) {
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
