package com.example.demo.Controller;
// By C. Strunge 06-12-2018

import com.example.demo.CoursesRepo;
import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CoursesRepo coursesRepo;

    @GetMapping("/courses/students/{id}")
    public String viewStudents(Model model, @PathVariable Long id){
        Course courses = coursesRepo.findById(id);
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("courses", courses);
        return "studentView";
    }
    @GetMapping("/student/create")
    public String createStudent(Model model){
        model.addAttribute("student", new Student());
        return "studentCreate";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute Student student){
        System.out.println(student.toString());
        studentRepo.save(student);
        return "redirect:/courses";
    }

    @GetMapping("/student/join/{id}")
    public String joinStudent(Model model, @PathVariable Long id){
        List<Student> students = studentRepo.findAll();
        Course course = coursesRepo.findById(id);
        model.addAttribute("student", students);
        model.addAttribute("currentCourse", course);
        return "joinStudent";
    }

    @GetMapping("/student/join/{courseId}/{studentId}")
    public String joinStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        Course courseStudent = coursesRepo.findById(courseId);
        Student studentCourse = studentRepo.findById(studentId);
        courseStudent.getStudents().add(studentCourse);
        coursesRepo.save(courseStudent);
        return "redirect:/courses";
    }
}
