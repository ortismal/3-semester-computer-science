package com.example.demo.Controller;

import com.example.demo.*;
import com.example.demo.Model.*;
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

    @Autowired
    private StudyProgrammesRepo studyProgrammesRepo;

    @GetMapping("/courses/create")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("studyProgramme", studyProgrammesRepo.findAll());
        return "courseCreate";
    }

    @PostMapping("/courses/create")
    public String saveCourse(Course course) {
        coursesRepo.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String viewCourse(Model model) {
        List<Course> courses = coursesRepo.findAll();
        model.addAttribute("coursesToBeSendToView", courses);
        return "coursesView";
    }


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
}

