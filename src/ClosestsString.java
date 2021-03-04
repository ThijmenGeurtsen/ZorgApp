import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestsString {
    //generate score list of type
    private static JSON json = new JSON();
    private static SmallFunctions smallFunc = new SmallFunctions();
    private int amountNames = 3;

    public List<String> closestsMatch(String name){
        List<List<String>> list = getScore(name);

        List<Integer> list2 = list.get(1).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());


        List<Integer> dontUseValues = new ArrayList();
        List<String> names = new ArrayList();

        List<Integer> copy = new ArrayList();

        for (int i=0; i<amountNames;i++) {
            //create coppy
            for (Integer num : dontUseValues){
                copy.add(num);
            }
            //get minimum value
            int[] nameSel = smallFunc.minValue(list2, copy);

            //add to don't use values
            dontUseValues.add(nameSel[0]);

            names.add(list.get(0).get(nameSel[1]));
        }
        return names;

        //List<Integer> sortedList = list2;
        //Collections.sort(sortedList);
    }

    //returns list depending on the name of the person
    public List<List<String>> getScore(String name){
        String[] splitted = name.split("\\s+");
        List<Integer> firstList;
        //get score list of scores of the name input
        if(name.indexOf(' ') >= 0) {
            firstList = listScore("firstName", splitted[0]);
        } else{
            firstList = listScore("firstName", name);
        }

        //doesn't run when last name isn't entered
        List<Integer> scores = new ArrayList();
        if (splitted.length==2) {
            List<Integer> second_list = listScore("lastName", splitted[1]);
            //combine two score lists
            for (int i = 0; i < firstList.size(); i++){
                int sum = firstList.get(i) + second_list.get(i);
                scores.add(sum);
            }
        }else{
            scores = firstList;
        }
        //get list of names
        List<String> names = json.list_patients();

        //combine lists
        List<List<String>> combined = new ArrayList();

        combined.add(names);

        //convert to string list
        List<String> scores_2 = new ArrayList<>(scores.size());
        for (Integer myInt : scores) {
            scores_2.add(String.valueOf(myInt));
        }
        //add newly formed string list
        combined.add(scores_2);

        return combined;
    }

    //return score list
    public List<Integer> listScore(String type, String i){
        List<String> name = json.generate_list(type);
        List<Integer> list = compareList(name, i);
        return list;

    }
    //returns list of scores of inpit list
    public List<Integer> compareList(List<String> names, String i) {
        List<Integer> scores = new ArrayList();
        for (int n = 0; n < names.size(); n++) {
            int add = calculate(names.get(n),i);
            scores.add(add);
        }
        return scores;

    }
    //////////////////////////////////////
    ///calculate difference between two strings
    /////////////////////////////////////////
    public int calculate(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculate(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculate(x, y.substring(1)) + 1;
        int deletion = calculate(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);


        //////////////////////////////////////
        //////////////////////////////////////
    }

}
