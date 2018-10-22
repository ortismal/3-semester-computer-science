package model;

public class Teacher extends Person {


    public Teacher(String name, String cpr) {
        super(name, cpr);
    }

    @Override
    public String doExam() {
        String exam = "I will ask the questions.";
        return exam;
    }
}
