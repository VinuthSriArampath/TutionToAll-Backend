// CourseController.java
package edu.icet.crm.controller;

import edu.icet.crm.model.Course;
import edu.icet.crm.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
