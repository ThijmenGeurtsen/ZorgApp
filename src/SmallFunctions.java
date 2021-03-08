import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmallFunctions {
    private Scanner scanner = new Scanner(System.in);

    public void sleep(int Sleep) {
        try {
            Thread.sleep(Sleep);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        scanner.nextLine();
    }
    public void line(){
        System.out.println("--------------------------------------");
    }

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

    public static int[] minValue(List<Integer> input, List<Integer> dontUseValues) {
        int min = 9999;
        int minIndex = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) < min && dontUseValues.contains(input.get(i)) == false) {
                minIndex = i;
                min = input.get(i);

            }
            //remove int out of list
            if (dontUseValues.contains(input.get(i))) {
                dontUseValues.remove(input.get(i));
            }

        }
        //return minIndex and min
        return new int[] {min,minIndex};
    }
}


