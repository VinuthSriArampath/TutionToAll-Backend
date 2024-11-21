package edu.icet.crm.controller;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.StudentRegisteredCourses;
import edu.icet.crm.service.CourseService;
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

    // ? GET parameters

    @GetMapping("/getAll") // -! get all COURSES
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }
    @GetMapping("/getByCourseId/{courseId}") // -! get COURSE by id
    public Course getCourseById(@PathVariable String courseId){
        return courseService.getCourseById(courseId);
    }
    @GetMapping("/search/{courseId}/institute/{instituteId}") // -! search a COURSE by id IN THE INSTITUTE
    public Course getCourseByInstituteId(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        return courseService.getCourseByIdInInstitute(courseId,instituteId);
    }
    @GetMapping("/getAll/{instituteId}") // -! get all COURSES in the INSTITUTE
    public List<Course> getAllCoursesByInstituteId(@PathVariable String instituteId){
        return courseService.getAllCourses(instituteId);
    }


    //? POST parameters

    @PostMapping("/add/{instituteId}") // -! add a COURSE to the institute
    public ResponseEntity<String> addCourse(@PathVariable String instituteId, @RequestBody Course course){
        String id = courseService.addCourse(instituteId, course);
        String returnString = "\""+id+"\"";
        return ResponseEntity.ok(returnString);
    }

    //? PUT/PATCH parameters

    @PatchMapping("/update/{instituteId}") // -! update the existing COURSE
    public void updateCourse(@PathVariable String instituteId,@RequestBody Course course){
        courseService.updateCourse(instituteId,course);
    }

    // ? DELETE parameters

    @DeleteMapping("/delete/{courseId}/from/{instituteId}") // -! Remove a COURSE from institute
    public void deleteCourse(@PathVariable("courseId") String courseId,@PathVariable("instituteId") String instituteId){
        courseService.deleteFromInstitute(instituteId,courseId);
    }

    // ? Course Student Related

    @PostMapping("/student/add") // -! add STUDENT to the course
    public void addStudentToCourse(@RequestBody StudentRegisteredCourses studentRegisteredCourses){
        courseService.addStudent(studentRegisteredCourses);
    }

    @DeleteMapping("/{courseId}/remove/student/{studentId}")// -! remove STUDENT from the course
    public void  removeStudentFromCourse(@PathVariable("courseId") String courseId,@PathVariable("studentId") String studentId){
        courseService.removeStudentFromCourse(courseId,studentId);
    }

    // ? Course Teacher Related

    @PostMapping("/{courseId}/teacher/add/{teacherId}") // -! add TEACHER to course
    public void addTeacherToCourse(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.addTeacherToCourse(courseId,teacherId);
    }
    @PatchMapping("/{courseId}/teachers/update/{teacherId}") // -! update the TEACHER of the course
    public void updateCourseTeacher(@PathVariable("courseId") String courseId,@PathVariable("teacherId") String teacherId){
        courseService.updateCourseTeacher(courseId,teacherId);
    }
}
