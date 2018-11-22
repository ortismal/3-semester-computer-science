package com.example.demo;

import com.example.demo.Model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepo extends CrudRepository<Course, Long> {
    Course findById(Long id);
    List<Course> findAll();
}
