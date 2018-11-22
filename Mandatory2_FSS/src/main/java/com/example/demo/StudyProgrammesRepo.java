package com.example.demo;

import com.example.demo.Model.StudyProgramme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudyProgrammesRepo extends CrudRepository<StudyProgramme, Long> {
    List<StudyProgramme> findAll();
}
