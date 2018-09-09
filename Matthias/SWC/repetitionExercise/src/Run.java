import java.util.ArrayList;

public class Run {
    /*
    3. Create a method called that calculates the age of a Person
     */

    public static void main(String[] args){

        ArrayList<String> courseList = new ArrayList<>();
        courseList.add("Dansk");
        courseList.add("Historie");
        courseList.add("Matematik");

        // 8. Create a main where 2 teachers and 3 students are created, store them in an array, and output their values
        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        teachers.add(new Teacher("Lotte", 12345678, "Rektor", courseList));
        teachers.add(new Teacher("Hans", 12345678, "Hjælpelærer", courseList));
        students.add(new Student("Kurt", 12345678, 1, 7));
        students.add(new Student("Svend", 12345678, 2, 12));
        students.add(new Student("Lise", 12345678, 3, 10));
        students.add(new Student("William", 12345678, 3, 10));

        // 6. Make sure that your toString function prints cpr no, name and student no for the student, and prints cpr no, name and title for the teacher.
        printArray(teachers);
        printArray(students);

        // 4. A student should have a student no, a grade average and a method to update the grade average.
        students.get(0).setGradeAvg(4);
        printArray(students);

        // 7. There should be a counter that automatically counts the number of students/teachers created
        System.out.println();
        System.out.println("Total amount of students: " + students.size() + " | Total amount of teachers: " + teachers.size());

        // 9. Make sure that you can compare 2 students and see if they are equal. The students are equal if they have the same student no.
        System.out.println();
        System.out.println("The students are equal: " + students.get(0).checkEqual(students.get(1)));
        System.out.println("The students are equal: " + students.get(2).checkEqual(students.get(3)));


    }

    public static void printArray(ArrayList<?> persons){
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).toString());
        }
        System.out.println("--------------------------------");
    }

}
