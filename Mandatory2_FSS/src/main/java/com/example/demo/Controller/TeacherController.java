package com.example.demo.Controller;
// By C. Strunge 06-12-2018

import com.example.demo.CoursesRepo;
import com.example.demo.Model.Course;
import com.example.demo.Model.Teacher;
import com.example.demo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private CoursesRepo coursesRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping("/teacher/create")
    public String createTeacher(Model model){
        model.addAttribute("teacher", new Teacher());
        return "teacherCreate";
    }

    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute Teacher teacher){
        System.out.println(teacher.toString());
        teacherRepo.save(teacher);
        return "redirect:/courses";
    }

    @GetMapping("/courses/teachers/{id}")
    public String viewTeachers(Model model, @PathVariable Long id){
        Course courses = coursesRepo.findById(id);
        model.addAttribute("teachers", teacherRepo.findAll());
        model.addAttribute("courses", courses);
        return "teacherView";
    }

    @GetMapping("/teacher/join/{id}")
    public String joinTeacher(Model model, @PathVariable Long id){
        List<Teacher> teachers = teacherRepo.findAll();
        Course course = coursesRepo.findById(id);
        model.addAttribute("teacher", teachers);
        model.addAttribute("currentCourse", course);
        return "joinTeacher";
    }

    @GetMapping("/teacher/join/{courseId}/{teacherId}")
    public String joinTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        Course courseTeacher = coursesRepo.findById(courseId);
        Teacher teacherCourse = teacherRepo.findById(teacherId);
        courseTeacher.getTeachers().add(teacherCourse);
        coursesRepo.save(courseTeacher);
        return "redirect:/courses";
    }

}
