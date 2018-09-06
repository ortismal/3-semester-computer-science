import java.util.*;

public class Run {
    public static void main (String[] args){
    ArrayList<String> coursesList = new ArrayList<>();
    ArrayList<Teacher> teachersList = new ArrayList<>();
    ArrayList<Double> gradesList = new ArrayList<>();
    ArrayList<Student> studentsList = new ArrayList<>();


    coursesList.add("Course A");
    coursesList.add("Course B");
    coursesList.add("Course C");
    coursesList.add("Course D");
    coursesList.add("Course E");

    teachersList.add(new Teacher("Karl", 1212121253L, "Elite teacher", coursesList));
    teachersList.add(new Teacher("John", 1212121255L, "Teacher", coursesList));

    gradesList.add(1.0);
    gradesList.add(2.0);
    gradesList.add(3.0);
    gradesList.add(4.0);
    gradesList.add(5.0);

    studentsList.add(new Student("Lars", 2345983337L, 1, gradesList));
    studentsList.add(new Student("Kevin", 2145782323L, 2, gradesList));
    studentsList.add(new Student("Ralf", 8525468789L, 3, gradesList));

    printList(teachersList);
    printList(studentsList);
    System.out.println("The students are equal: " + studentsList.get(0).equality(studentsList.get(1)));
    System.out.println("Total amount of persons: " + Person.countPersons);

    }
    public static void printList(ArrayList<?> info){
        for(int i = 0; i < info.size(); i++){
            System.out.println(info.get(i).toString());
        }
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
    }

}
