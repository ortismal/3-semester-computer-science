import java.util.ArrayList;

public class Teacher extends Person {
    private String title;
    private ArrayList<String> courses;

    public Teacher(String name, long cpr, String title, ArrayList<String> courses) {
        super(name, cpr);
        this.title = title;
        this.courses = courses;
    }

    @Override
    public String toString(){
        return "CPR: " + cpr + " Name: " + name + " Title: " + title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList courses) {
        this.courses = courses;
    }
}
