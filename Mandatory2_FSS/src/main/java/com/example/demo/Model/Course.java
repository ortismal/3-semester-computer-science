package com.example.demo.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String NOC_danish;
    private String NOC_english;
    private String mandatory_elective;
    private Integer ects;
    private String courseLanguage;
    private Integer minOfStudents;
    private Integer expOfStudents;
    private Integer maxOfStudents;
    private String prerequisites;
    private String learningsOutcome;
    private String content;
    private String learningActivities;
    private String examForm;
    private Integer semester;
    private String classCode;

    @ManyToOne
    private StudyProgramme studyProgramme;

    @ManyToMany
    @JoinColumn(name = "id")
    private List<Student> students;



    public Course() {
    }


        public void addStudent(Student student){
        students.add(student);
    }

    public Course(String NOC_danish, String NOC_english, String mandatory_elective, Integer ects, String courseLanguage,
                  Integer minOfStudents, Integer expOfStudents, Integer maxOfStudents, String prerequisites,
                  String learningsOutcome, String content, String learningActivities, String examForm, Integer semester,
                  String classCode, StudyProgramme studyProgramme, List<Student> students) {

        this.NOC_danish = NOC_danish;
        this.NOC_english = NOC_english;
        this.mandatory_elective = mandatory_elective;
        this.ects = ects;
        this.courseLanguage = courseLanguage;
        this.minOfStudents = minOfStudents;
        this.expOfStudents = expOfStudents;
        this.maxOfStudents = maxOfStudents;
        this.prerequisites = prerequisites;
        this.learningsOutcome = learningsOutcome;
        this.content = content;
        this.learningActivities = learningActivities;
        this.examForm = examForm;
        this.semester = semester;
        this.classCode = classCode;
        this.studyProgramme = studyProgramme;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getNOC_danish() {
        return NOC_danish;
    }

    public void setNOC_danish(String NOC_danish) {
        this.NOC_danish = NOC_danish;
    }

    public String getNOC_english() {
        return NOC_english;
    }

    public void setNOC_english(String NOC_english) {
        this.NOC_english = NOC_english;
    }

    public String getMandatory_elective() {
        return mandatory_elective;
    }

    public void setMandatory_elective(String mandatory_elective) {
        this.mandatory_elective = mandatory_elective;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String getCourseLanguage() {
        return courseLanguage;
    }

    public void setCourseLanguage(String courseLanguage) {
        this.courseLanguage = courseLanguage;
    }

    public Integer getMinOfStudents() {
        return minOfStudents;
    }

    public void setMinOfStudents(Integer minOfStudents) {
        this.minOfStudents = minOfStudents;
    }

    public Integer getExpOfStudents() {
        return expOfStudents;
    }

    public void setExpOfStudents(Integer expOfStudents) {
        this.expOfStudents = expOfStudents;
    }

    public Integer getMaxOfStudents() {
        return maxOfStudents;
    }

    public void setMaxOfStudents(Integer maxOfStudents) {
        this.maxOfStudents = maxOfStudents;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getLearningsOutcome() {
        return learningsOutcome;
    }

    public void setLearningsOutcome(String learningsOutcome) {
        this.learningsOutcome = learningsOutcome;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLearningActivities() {
        return learningActivities;
    }

    public void setLearningActivities(String learningActivities) {
        this.learningActivities = learningActivities;
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public StudyProgramme getStudyProgramme() {
        return studyProgramme;
    }

    public void setStudyProgramme(StudyProgramme studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
