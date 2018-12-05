package com.example.demo.Controller;

import com.example.demo.*;
import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CoursesController {
    @Autowired
    private CoursesRepo coursesRepo;

    //Rykkes til "studentController"
    @Autowired
    private StudentRepo studentRepo;

    //Rykkes til "teacherController"
    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudyProgrammesRepo studyProgrammesRepo;

    @GetMapping("/courses/create")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "courseCreate";
    }

    @PostMapping("/courses/create")
    public ResponseEntity<Course> saveCourse(Course course) {
        Course newCourse = coursesRepo.save(course);

        return new ResponseEntity(newCourse, HttpStatus.OK);
    }

    @GetMapping("/courses")
    public String viewCourse(Model model) {
        List<Course> courses = coursesRepo.findAll();
        model.addAttribute("coursesToBeSendToView", courses);
        return "coursesView";
    }

    // Rykkes til "studentController"
    @GetMapping("/courses/students/{id}")
    public String viewStudents(Model model, @PathVariable Long id){
        Course courses = coursesRepo.findById(id);
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("courses", courses);
        return "studentView";
    }

//    //Rykkes til "studentController"
//    @DeleteMapping("courses/students/delete/{id}")
//    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
//        Student student = studentRepo.findById(id);
//        coursesRepo.deleteById(id);
//        return new ResponseEntity(student, HttpStatus.OK);
//    }

    @GetMapping("/courses/edit/{id}")
    public String editCourse(Model model, @PathVariable Long id) {
        Course course = coursesRepo.findById(id);
        model.addAttribute("course", course);
        List<StudyProgramme> studyProgrammes = studyProgrammesRepo.findAll();
        model.addAttribute("studyProgrammes", studyProgrammes);
        List<Course> coursesList = coursesRepo.findAll();
        model.addAttribute("courses", coursesList);
        return "courseEdit";
    }

    @ResponseBody
    @PutMapping("/courses/edit/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id, @RequestParam String NOC_danish, @RequestParam String NOC_english, @RequestParam String mandatory_elective, @RequestParam Integer ects,
            @RequestParam String courseLanguage, @RequestParam Integer minOfStudents, @RequestParam Integer expOfStudents,
            @RequestParam Integer maxOfStudents, @RequestParam String prerequisites, @RequestParam String learningsOutcome,
            @RequestParam String content, @RequestParam String learningActivities, @RequestParam String examForm,
            @RequestParam Integer semester, @RequestParam String classCode, @RequestParam StudyProgramme studyProgramme) {


        Course course = new Course(NOC_danish, NOC_english, mandatory_elective, ects, courseLanguage, minOfStudents,
                expOfStudents, maxOfStudents, prerequisites, learningsOutcome, content, learningActivities, examForm,
                semester, classCode, studyProgramme);

        Course courseToBeUpdated = coursesRepo.findById(id);


        courseToBeUpdated.setNOC_danish(course.getNOC_danish());
        courseToBeUpdated.setNOC_english(course.getNOC_english());
        courseToBeUpdated.setMandatory_elective(course.getMandatory_elective());
        courseToBeUpdated.setEcts(course.getEcts());
        courseToBeUpdated.setCourseLanguage(course.getCourseLanguage());
        courseToBeUpdated.setMinOfStudents(course.getMinOfStudents());
        courseToBeUpdated.setExpOfStudents(course.getExpOfStudents());
        courseToBeUpdated.setMaxOfStudents(course.getMaxOfStudents());
        courseToBeUpdated.setPrerequisites(course.getPrerequisites());
        courseToBeUpdated.setLearningsOutcome(course.getLearningsOutcome());
        courseToBeUpdated.setContent(course.getContent());
        courseToBeUpdated.setLearningActivities(course.getLearningActivities());
        courseToBeUpdated.setExamForm(course.getExamForm());
        courseToBeUpdated.setSemester(course.getSemester());
        courseToBeUpdated.setClassCode(course.getClassCode());
        courseToBeUpdated.setStudyProgramme(course.getStudyProgramme());


        coursesRepo.save(courseToBeUpdated);
        return new ResponseEntity(courseToBeUpdated, HttpStatus.OK);
    }

    @DeleteMapping("courses/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {

        Course course = coursesRepo.findById(id);
        coursesRepo.delete(id);

        return new ResponseEntity(course, HttpStatus.OK);
    }

    //Rykkes til "studentController"
    @GetMapping("/student/create")
    public String createStudent(Model model){
        model.addAttribute("student", new Student());
        return "studentCreate";
    }

    //Rykkes til "studentController"
    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute Student student){
        System.out.println(student.toString());
        studentRepo.save(student);
        return "redirect:/courses";
    }

    //Rykkes til "studentController"
    @GetMapping("/student/join/{id}")
    public String joinStudent(Model model, @PathVariable Long id){
        List<Student> students = studentRepo.findAll();
        Course course = coursesRepo.findById(id);
        model.addAttribute("student", students);
        model.addAttribute("currentCourse", course);
        return "joinStudent";
    }

    //Rykkes til "studentController"
    @GetMapping("/student/join/{courseId}/{studentId}")
    public String joinStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        Course courseStudent = coursesRepo.findById(courseId);
        Student studentCourse = studentRepo.findById(studentId);
        courseStudent.getStudents().add(studentCourse);
        coursesRepo.save(courseStudent);
        return "redirect:/courses";
    }

    //Rykkes til "teacherController"
    @GetMapping("/teacher/create")
    public String createTeacher(Model model){
        model.addAttribute("teacher", new Teacher());
        return "teacherCreate";
    }

    //Rykkes til "teacherController"
    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute Teacher teacher){
        System.out.println(teacher.toString());
        teacherRepo.save(teacher);
        return "redirect:/courses";
    }

    //Rykkes til "teacherController"
    @GetMapping("/courses/teachers/{id}")
    public String viewTeachers(Model model, @PathVariable Long id){
        Course courses = coursesRepo.findById(id);
        model.addAttribute("teachers", teacherRepo.findAll());
        model.addAttribute("courses", courses);
        return "teacherView";
    }

//    //Rykkes til "teacherController"
//    @DeleteMapping("courses/teacher/delete/{id}")
//    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Long id) {
//        Teacher teacher = teacherRepo.findById(id);
//        teacherRepo.delete(id);
//
//        return new ResponseEntity(teacher, HttpStatus.OK);
//    }

    //Rykkes til "teacherController"
    @GetMapping("/teacher/join/{id}")
    public String joinTeacher(Model model, @PathVariable Long id){
        List<Teacher> teachers = teacherRepo.findAll();
        Course course = coursesRepo.findById(id);
        model.addAttribute("teacher", teachers);
        model.addAttribute("currentCourse", course);
        return "joinTeacher";
    }

    //Rykkes til "teacherController"
    @GetMapping("/teacher/join/{courseId}/{teacherId}")
    public String joinTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        Course courseTeacher = coursesRepo.findById(courseId);
        Teacher teacherCourse = teacherRepo.findById(teacherId);
        courseTeacher.getTeachers().add(teacherCourse);
        coursesRepo.save(courseTeacher);
        return "redirect:/courses";
    }

}

