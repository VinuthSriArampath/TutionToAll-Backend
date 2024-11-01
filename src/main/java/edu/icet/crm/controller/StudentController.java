package edu.icet.crm.controller;

import edu.icet.crm.model.Student;
import edu.icet.crm.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/register")
    public void registerStudent(@Valid @RequestBody Student student){
        studentService.registerStudent(student);
    }
    @GetMapping("/search/{id}")
    public Student searchStudentById(@PathVariable String id){
        return studentService.searchStudentById(id);
    }
}
