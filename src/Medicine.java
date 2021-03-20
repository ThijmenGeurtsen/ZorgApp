public class Medicine {
    private String name;
    private String desc;
    private double dosis;


    Medicine(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


    public String getDescription() {
        return desc;
    }

    public String getType() {
        return name;
    }

    public double getDosis() {
        return dosis;
    }

    public void setDosis(double i) {
        dosis=i;
    }
}

