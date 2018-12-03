package com.example.demo;

import com.example.demo.Model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepo extends CrudRepository<Teacher, Long> {
    Teacher findById(Long id);
    List<Teacher> findAll();
}
