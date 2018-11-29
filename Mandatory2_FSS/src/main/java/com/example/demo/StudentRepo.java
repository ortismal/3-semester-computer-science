package com.example.demo;

import com.example.demo.Model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepo extends CrudRepository<Student, Long> {
        Student findById(Long id);
        List<Student> findAll();
        }

