package edu.icet.crm.controller;

import edu.icet.crm.model.Course;
import edu.icet.crm.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add/{instituteId}")
    public void addCourse(@PathVariable String instituteId, @Valid @RequestBody Course course){
        courseService.addCourse(instituteId, course);
    }
    @GetMapping("/getAll/{instituteId}")
    public List<Course> getCourses(@PathVariable String instituteId){
        return courseService.getAllCourses(instituteId);
    }
    @DeleteMapping("/delete/{instituteId}")
    public void deleteCourse(@PathVariable String instituteId){
        courseService.deleteInstitute();
    }
}
