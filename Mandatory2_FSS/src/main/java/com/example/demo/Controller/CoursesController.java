package com.example.demo.Controller;

import com.example.demo.CoursesRepo;
import com.example.demo.Model.Course;
import com.example.demo.Model.StudyProgramme;
import com.example.demo.StudyProgrammesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CoursesController {
    @Autowired
    private CoursesRepo coursesRepo;

//    @ResponseBody
//    public String saveCourse(
//            @RequestParam
//                    String NOC_danish,
//            @RequestParam
//                    String NOC_english,
//            @RequestParam
//                    String mandatory_elective,
//            @RequestParam
//                    Integer ects,
//            @RequestParam
//                    String courseLanguage,
//            @RequestParam
//                    Integer minOfStudents,
//            @RequestParam
//                    Integer expOfStudents,
//            @RequestParam
//                    Integer maxOfStudents,
//            @RequestParam
//                    String prerequisities,
//            @RequestParam
//                    String learningsOutcome,
//            @RequestParam
//                    String content,
//            @RequestParam
//                    String learningActivities,
//            @RequestParam
//                    String examForm,
//            @RequestParam
//                    Integer semester,
//            @RequestParam
//                    String classCode,
//            @RequestParam
//                    StudyProgramme studyProgramme) {
//        Course course = new Course(NOC_danish, NOC_english, mandatory_elective, ects, courseLanguage, minOfStudents,
//                expOfStudents, maxOfStudents, prerequisities, learningsOutcome, content, learningActivities, examForm, semester, classCode, studyProgramme);
//
//        coursesRepo.save(course);
//
//        return "OK";
//    }


    @GetMapping("/courses/create")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "courseCreate";
    }

    @PostMapping("/courses/create")
    public ResponseEntity<Course> saveCourse(Course course){
        Course newCourse = coursesRepo.save(course);

        return new ResponseEntity(newCourse, HttpStatus.OK);
    }
}

