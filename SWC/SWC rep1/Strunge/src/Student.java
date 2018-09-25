import java.util.*;

public class Student extends Person {
    private int studentNo;
    private ArrayList<Double> gradeAvg;
    public double totalAvg = 0.0;

    public Student(String name, long cpr, int studentNo, ArrayList<Double> gradeAvg) {
        super(name, cpr);
        this.studentNo = studentNo;
        this.gradeAvg = gradeAvg;
    }

    @Override
    public String toString(){
        return "CPR: " + cpr + " Name: " + name + " StudentNo: " + studentNo;
    }

    public boolean equality(Student studentCheck){
        return this.studentNo == studentCheck.studentNo;
    }

    public  ArrayList<Double> gradeSet(){
        gradeAvg.add(2.0);
        gradeAvg.add(1.0);
        System.out.println(gradeAvg + " Is grades");

        return (ArrayList<Double>) gradeAvg;
    }
    public ArrayList<Double> gradeUpdate(){
        gradeAvg.set(0,3.0);
        gradeAvg.set(1,4.0);
        System.out.println(gradeAvg + " Is grades updated");

        return (ArrayList<Double>) gradeAvg;
    }
    public double calcAvg(){
        countGrade = gradeAvg.size();
        System.out.println(countGrade + " Is count");
        double sum = 0.0;
        for(double i : gradeAvg){
            sum += i;
            System.out.println(sum + " Is sum");
            totalAvg = sum/countGrade;
            System.out.println(totalAvg + " Is avg.");
            //--Laves til toString
            //--> ERROR: System.out.println(tester.toString() + "TEST");
        }
        return  totalAvg;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public ArrayList<Double> getGradeAvg() {
        return gradeAvg;
    }

    public void setGradeAvg(ArrayList<Double> gradeAvg) {
        this.gradeAvg = gradeAvg;
    }

}
