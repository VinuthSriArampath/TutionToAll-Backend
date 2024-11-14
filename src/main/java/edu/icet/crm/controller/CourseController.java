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


    @GetMapping("/getAll")
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }
    @GetMapping("/getByCourseId/{courseId}")
    public Course getCourseById(@PathVariable String courseId){
        return courseService.getCourseById(courseId);
    }
    @GetMapping("/search/{courseId}/institute/{instituteId}")
    public Course getCourseByInstituteId(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        return courseService.getCourseByIdInInstitute(courseId,instituteId);
    }
    @GetMapping("/getAll/{instituteId}")
    public List<Course> getAllCoursesByInstituteId(@PathVariable String instituteId){
        return courseService.getAllCourses(instituteId);
    }


    @PostMapping("/add/{instituteId}")
    public ResponseEntity<String> addCourse(@PathVariable String instituteId, @RequestBody Course course){
        String id = courseService.addCourse(instituteId, course);
        String returnString = "\""+id+"\"";
        return ResponseEntity.ok(returnString);
    }


    @PatchMapping("/update/{instituteId}")
    public void updateCourse(@PathVariable String instituteId,@Valid @RequestBody Course course){
        courseService.updateCourse(instituteId,course);
    }

    @DeleteMapping("/delete/{courseId}/from/{instituteId}")
    public void deleteCourse(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        courseService.deleteFromInstitute(instituteId,courseId);
    }

    // ? Course Student Related

    @PostMapping("/student/add")
    public void addStudentToCourse(@RequestBody StudentRegisteredCourses studentRegisteredCourses){
        courseService.addStudent(studentRegisteredCourses);
    }

    @DeleteMapping("/{courseId}/remove/student/{studentId}")
    public void  removeStudentFromCourse(@PathVariable("courseId") String courseId,@PathVariable("studentId") String studentId){
        courseService.removeStudentFromCourse(courseId,studentId);
    }

    // ? Course Teacher Related

    @PostMapping("/{courseId}/teacher/add/{teacherId}")
    public void addTeacherToCourse(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.addTeacherToCourse(courseId,teacherId);
    }
    @PatchMapping("/{courseId}/teachers/update/{teacherId}")
    public void updateCourseTeacher(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.updateCourseTeacher(courseId,teacherId);
    }
}
