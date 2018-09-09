import java.util.ArrayList;

// 1. Create 2 new classes Student and Teacher (use the Person class)
public class Teacher extends Person {

    // 5. A Teacher should have an title, and a list of courses being currently taught
    private String title;
    private ArrayList<String> courseList;

    public Teacher(String name, long CPR, String title, ArrayList<String> courseList) {
        super(name, CPR);
        this.title = title;
        this.courseList = courseList;
    }

    public Teacher() {
    }

    @Override // 2. Override a method in both Student and Teacher
    public String toString(){
        // toString der skal printe cpr-nummer, navn & student no.
        return "Name: " + getName() + " | CPR: " + getCPR() + " | Title: " + title;
    }

}
