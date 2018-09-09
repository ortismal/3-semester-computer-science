public class Person {

    private String name;
    private long CPR;

    public Person(String name, long CPR) {
        this.name = name;
        this.CPR = CPR;
    }

    public Person() {

    }

    public String toString(){
        return "Name: " + name + " | CPR: " + CPR;
    }

    public int ageFromCpr(){
        // Metode der udregner en persons alder ud fra CPR long.
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCPR() {
        return CPR;
    }

    public void setCPR(long CPR) {
        this.CPR = CPR;
    }
}

