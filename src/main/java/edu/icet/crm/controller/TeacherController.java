package edu.icet.crm.controller;

import edu.icet.crm.model.Teacher;
import edu.icet.crm.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @PostMapping("/register")
    public void registerStudent(@Valid @RequestBody Teacher teacher){
        teacherService.registerTeacher(teacher);
    }
    @GetMapping("/search/{id}")
    public Teacher searchStudentById(@PathVariable String id){
        return teacherService.searchTeacherById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id){
        teacherService.deleteTeacher(id);
    }
    @PatchMapping("/update")
    public void updateStudent(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
    }
    @GetMapping("/all")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }
}
