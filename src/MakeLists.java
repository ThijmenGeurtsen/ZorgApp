import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeLists {

    public List<Profile> listPatients() {
        //generate medicine list
        MakeLists listInfo = new MakeLists();
        List<Medicine> meds = listInfo.listMedicine();


        Weight weight1 = new Weight(10,3,2020, 60);
        Weight weight2 = new Weight(11,3,2020, 65);
        Weight weight3 = new Weight(12,3,2020, 600);
        List<Weight> weights = new ArrayList<>();
        weights.add(weight1);
        weights.add(weight2);
        weights.add(weight3);

        Medicine med1 = meds.get(0);
        Medicine med2 = meds.get(1);
        Medicine med3 = meds.get(2);
        Medicine med4 = meds.get(3);
        med1.setDosis(50);
        med2.setDosis(200);
        med3.setDosis(25);
        med4.setDosis(20);
        List<Medicine> medList = new ArrayList<>();
        medList.add(med1);
        medList.add(med2);
        medList.add(med3);
        medList.add(med4);




        Profile profile1 = new Profile(
                "Lokesh"
                , "Gupta"
                , 80
                , 160
                , weights
                , medList);

        System.out.println(profile1.getMedicine());
        Profile profile2 = new Profile(
                "Dwayne"
                , "Woodward"
                , 40
                , 180
                , weights
                , medList);

        Profile profile3 = new Profile(
                "Alanna"
                , "Villanueva"
                , 30
                , 190
                , weights
                , medList);

        Profile profile4 = new Profile(
                "Alanso" //Alonso
                , "Andrews"
                , 30
                , 190
                , weights
                , medList);
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        profiles.add(profile3);
        profiles.add(profile4);
        return profiles;
    }
    public List<Medicine> listMedicine(){
        Medicine med1 = new Medicine(
                "altretamine",
                "Altretamine is een antineoplastisch middel. Het werd in 1990 goedgekeurd door de Amerikaanse FDA.");
        Medicine med2 = new Medicine(
                "amiodarone tablet"
                , "Amiodaron laat het hart langzamer en regelmatiger kloppen."
        );
        Medicine med3 = new Medicine(
                "amitriptyline tablet"
                , "Amitriptyline verbetert uw stemming en maakt u minder angstig. Hierdoor heeft u minder last van klachten als piekeren, slaapproblemen en prikkelbaarheid. Ook werkt amitriptyline tegen pijnklachten."
        );
        Medicine med4 = new Medicine(
                "citalopram"
                ,"Citalopram verbetert de stemming en vermindert angsten en angstverschijnselen (zoals piekeren, slaapproblemen, prikkelbaarheid en trillen). Het vertraagt ook de zaadlozing."
        );
        Medicine med5 = new Medicine("escitalopram"
                ,"Escitalopram verbetert uw stemming en vermindert angsten en angstverschijnselen zoals piekeren, slaapproblemen, prikkelbaarheid en trillen."
        );
        Medicine med6 = new Medicine(
                "fluoxetine"
                ,"Fluoxetine verbetert uw stemming en vermindert angsten en angstverschijnselen (zoals piekeren, slaapproblemen, prikkelbaarheid en trillen)."
        );

        Medicine med7 = new Medicine("fluvoxamine",
                "Fluvoxamine verbetert de stemming en vermindert angsten en angstverschijnselen (zoals piekeren, slaapproblemen, prikkelbaarheid en trillen)."
        );
        Medicine med8 = new Medicine(
                "paroxetine"
                ,"Paroxetine verbetert uw stemming en maakt u minder angstig. Het vertraagt ook de zaadlozing."
        );
        Medicine med9 = new Medicine(
                "sertraline"
                ,"Sertraline verbetert uw stemming en maakt u minder angstig. Hierdoor heeft u minder last van piekeren, slaapproblemen, prikkelbaarheid en trillen. Ook vertraagt sertraline de zaadlozing."
        );
        Medicine med10 = new Medicine(
                "vortioxetine"
                ,"Vortioxetine verbetert de stemming."
        );


        List<Medicine> medicines = new ArrayList<>();
        medicines.add(med1);
        medicines.add(med2);
        medicines.add(med3);
        medicines.add(med4);
        medicines.add(med5);
        medicines.add(med6);
        medicines.add(med7);
        medicines.add(med8);
        medicines.add(med9);
        medicines.add(med10);

        return medicines;

    }
}
