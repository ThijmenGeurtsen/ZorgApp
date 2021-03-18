import java.util.List;
import java.util.stream.IntStream;

public class Weight {
    private int day;
    private int month;
    private int year;
    private double weight;

    Weight(int day, int month, int year, double weight ){

        this.day = day;
        this.month = month;
        this.year = year;
        this.weight = weight;

    }
    /////////////////////
    /////   get    /////
    ////////////////////
    public String getDate() {
        return (year+"-"+month+"-"+day);
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public double getWeight() {
        return weight;
    }

    /////////////////////
    /////   set    /////
    ////////////////////
    public void setDay(int i) {
        day = i;
    }
    public void setMonth(int i) {
        month = i;
    }
    public void setYear(int i) {
        day = i;
    }
    public void setWeight(double i) {
        weight = i;
    }

    /////////////////////
    //  display weight //
    /////////////////////



}
