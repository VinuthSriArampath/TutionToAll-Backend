package edu.icet.crm.controller;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.StudentRegisteredCourses;
import edu.icet.crm.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add/{instituteId}")
    public ResponseEntity<String> addCourse(@PathVariable String instituteId, @RequestBody Course course){
        String id = courseService.addCourse(instituteId, course);
        String returnString = "\""+id+"\"";
        return ResponseEntity.ok(returnString);
    }
    @GetMapping("/getAll/{instituteId}")
    public List<Course> getCourses(@PathVariable String instituteId){
        return courseService.getAllCourses(instituteId);
    }
    @GetMapping("/getAll")
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }
    @GetMapping("/search/{courseId}/institute/{instituteId}")
    public Course getCourseByIdInInstitute(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        return courseService.getCourseByIdInInstitute(courseId,instituteId);
    }
    @DeleteMapping("/delete/{courseId}/of/{instituteId}")
    public void deleteCourse(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        courseService.deleteFromInstitute(instituteId,courseId);
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
    @PatchMapping("/{courseId}/teachers/update/{teacherId}")
    public void updateTeacher(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.updateTeacher(courseId,teacherId);
    }
    @GetMapping("/getCourseById/{courseId}")
    public Course searchCourseById(@PathVariable String courseId){
        return courseService.getCourseById(courseId);
    }
}
