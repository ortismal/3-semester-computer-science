// 1. Create 2 new classes Student and Teacher (use the Person class)
public class Student extends Person{

    private int studentNo;
    private int gradeAvg;

    public Student(String name, long CPR, int studentNo, int gradeAvg) {
        super(name, CPR);
        this.studentNo = studentNo;
        this.gradeAvg = gradeAvg;
    }

    public Student(){
    }

    // 4. A student should have a student no, a grade average and a method to update the grade average.
    public void setGradeAvg(int gradeAvg) {
        this.gradeAvg = gradeAvg;
    }

    @Override // 2. Override a method in both Student and Teacher
    public String toString(){
        // toString der skal printe cpr-nummer, navn & student no.
        return "Name: " + getName() + " | CPR: " + getCPR() + " | Student no: " + studentNo + " | Grade Average: " + gradeAvg;
    }

    // 9. Make sure that you can compare 2 students and see if they are equal. The students are equal if they have the same student no.
    public boolean checkEqual(Student s2){
        return(this.studentNo == s2.studentNo);
    }
}
