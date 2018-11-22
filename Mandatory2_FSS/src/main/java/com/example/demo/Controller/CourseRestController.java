package com.example.demo.Controller;

import com.example.demo.CoursesRepo;
import com.example.demo.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseRestController {

    @Autowired
    private CoursesRepo coursesRepo;


}
