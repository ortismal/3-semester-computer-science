package com.example.demo.Controller;

import com.example.demo.CoursesRepo;
import com.example.demo.Model.Course;
import com.example.demo.Model.StudyProgramme;
import com.example.demo.StudyProgrammesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoursesController {
    @Autowired
    private CoursesRepo coursesRepo;

    @GetMapping("/courses/create")
    public String addCourse(Model model){
        model.addAttribute("course", new Course());
//        List<StudyProgramme> studyProgrammes = StudyProgrammesRepo.findAll();
        return "index";
    }
}

