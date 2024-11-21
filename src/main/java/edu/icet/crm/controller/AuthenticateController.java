package edu.icet.crm.controller;

import edu.icet.crm.model.LoginUser;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.service.StudentService;
import edu.icet.crm.service.TeacherService;
import edu.icet.crm.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final InstituteService instituteService;

    // ! Student Login Authentication

    @PostMapping("/student/login")
    public ResponseEntity<ResponseMessage> authenticateStudent(@RequestBody LoginUser loginUser){
        return studentService.authenticateStudentLogin(loginUser);
    }

    // ! Teacher Login Authentication

    @PostMapping("/teacher/login")
    public ResponseEntity<ResponseMessage> authenticateTeacher(@RequestBody LoginUser loginUser){
        return teacherService.authenticateTeacherLogin(loginUser);
    }

    // ! Institute Login Authentication

    @PostMapping("/institute/login")
    public ResponseEntity<ResponseMessage> authenticateInstitute(@RequestBody LoginUser loginUser){
        return instituteService.authenticateInstituteLogin(loginUser);
    }
}
