public class Person {
    protected String name;
    protected long cpr;
    protected int countGrade = 0;
    public static int countPersons;

    public Person(String name, long cpr){
        this.name = name;
        this.cpr = cpr;
        countPersons++;
    }

    public String toString(){
        return "Name: " + name + "CPR: " + cpr;
    }
    /*public int ageCalc(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCpr() {
        return cpr;
    }

    public void setCpr(long cpr) {
        this.cpr = cpr;
    }*/
}
