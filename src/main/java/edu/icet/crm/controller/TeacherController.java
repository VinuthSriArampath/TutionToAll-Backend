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

    // ? GET parameters

    @GetMapping("/all") // -! GET ALL TEACHERS
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/search/{id}")// -! GET TEACHER BY ID
    public Teacher searchStudentById(@PathVariable String id){
        return teacherService.searchTeacherById(id);
    }

    // ? POST parameters
    @PostMapping("/register")// -! REGISTER TEACHER
    public void registerStudent(@Valid @RequestBody Teacher teacher){
        teacherService.registerTeacher(teacher);
    }

    // ? PATCH parameters

    @PatchMapping("/update") // -! UPDATE TEACHER
    public void updateStudent(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
    }

    @PatchMapping("/{teacherId}/updatePassword")
    public void updateTeacherPassword(@PathVariable String teacherId,@RequestBody String password){
        teacherService.updateTeacherPassword(teacherId,password);
    }
    // ? DELETE parameters

    @DeleteMapping("/delete/{id}")// -! DELETE TEACHER
    public void deleteStudent(@PathVariable String id){
        teacherService.deleteTeacher(id);
    }
}
