package edu.icet.crm.controller;

import edu.icet.crm.model.Student;
import edu.icet.crm.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    // ? GET parameters

    @GetMapping("/all")// -! GET ALL STUDENTS
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/search/{id}")// -! GET STUDENT BY ID
    public Student searchStudentById(@PathVariable String id){
        return studentService.searchStudentById(id);
    }

    // ? POST parameters

    @PostMapping("/register")// -! REGISTER STUDENT
    public void registerStudent(@Valid @RequestBody Student student){
        studentService.registerStudent(student);
    }

    //? PATCH parameters

    @PatchMapping("/update") // -! UPDATE STUDENT
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @PatchMapping("/{studentId}/updatePassword")
    public void updateStudentPassword(@PathVariable String studentId, @RequestBody String password){
        studentService.updateStudentPassword(studentId, password);
    }
    // ? DELETE parameters

    @DeleteMapping("/delete/{id}")// -! DELETE STUDENT
    public void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }

}
