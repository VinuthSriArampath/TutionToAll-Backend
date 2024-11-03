package edu.icet.crm.controller;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.StudentRegisteredCourses;
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
    @GetMapping("/search/{courseId}/institute/{instituteId}")
    public Course getCourseByIdInInstitute(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        return courseService.getCourseByIdInInstitute(courseId,instituteId);
    }
    @DeleteMapping("/delete/{courseId}/of/{instituteId}")
    public void deleteCourse(@PathVariable("instituteId") String instituteId,@PathVariable("courseId") String courseId){
        courseService.deleteInstitute(instituteId,courseId);
    }
    @PatchMapping("/update/{instituteId}")
    public void updateCourse(@PathVariable String instituteId,@Valid @RequestBody Course course){
        courseService.updateCourse(instituteId,course);
    }
    @PostMapping("/students/add")
    public void addStudent(@RequestBody StudentRegisteredCourses studentRegisteredCourses){
        courseService.addStudent(studentRegisteredCourses);
    }
    @PostMapping("/{courseId}/teachers/add/{teacherId}")
    public void addTeacher(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.addTeacher(courseId,teacherId);
    }
}
