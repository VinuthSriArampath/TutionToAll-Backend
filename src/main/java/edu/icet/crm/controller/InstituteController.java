package edu.icet.crm.controller;

import edu.icet.crm.model.Institute;
import edu.icet.crm.model.RegisteredStudents;
import edu.icet.crm.model.RegisteredTeachers;
import edu.icet.crm.service.EmailService;
import edu.icet.crm.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/institutes")
public class InstituteController {
    private final InstituteService instituteService;
    private final EmailService emailService;
    private final Random random = new Random(); // ! USES IN THE OTP SENDING

    // ? GET parameters
    @GetMapping("/all") // -! GET ALL INSTITUTIONS
    public List<Institute> getAllInstitutes(){
        return instituteService.getAllInstitute();
    }
    @GetMapping("/search/{id}") // -! GET AN INSTITUTE BY ID
    public Institute searchInstituteById(@PathVariable String id) {
        return instituteService.getInstituteById(id);
    }

    // ? POST parameters

    @PostMapping("/register") // -! REGISTER AN INSTITUTE
    public void registerInstitute(@RequestBody Institute institute) {
        instituteService.registerInstitutes(institute);
    }

    // ? PATCH parameters

    @PatchMapping("/update") // -! UPDATE AN INSTITUTE
    public void updateInstitute(@RequestBody Institute institute) {
        instituteService.updateInstitute(institute);
    }

    @PatchMapping("/{instituteId}/updatePassword")
    public void updateInstitutePassword(@PathVariable String instituteId,@RequestBody String password){
        instituteService.updateInstitutePassword(instituteId,password);
    }

    // ? DELETE parameters

    @DeleteMapping("/delete/{id}") // -! DELETE AN INSTITUTE
    public void deleteInstitute(@PathVariable String id) {
        instituteService.deleteInstitute(id);
    }


    // ! Institute OTP SENDING..

    @GetMapping("/otp/{email}") // -! SENDS THE OTP TO INSTITUTE EMAIL
    public int generateOtp(@PathVariable String email) {
        int otp = random.nextInt(100000, 999999); // ! GENERATES THE RANDOM NUMBER WHICH IS THE OTP NUMBER
        emailService.sendInstituteOtpEmail(email, "Institute Email Verification",String.valueOf(otp));
        return otp;
    }

    // ?  Institute Students

    @PostMapping("/students/add") // -! ADD A STUDENTS TO THE INSTITUTE
    public void addStudent(@RequestBody RegisteredStudents regStudents){
        instituteService.addStudent(regStudents);
    }
    @DeleteMapping("{instituteId}/students/remove/{studentId}") // -! REMOVE THE STUDENT FORM THE INSTITUTE
    public void removeStudent(@PathVariable("instituteId") String instituteId,@PathVariable("studentId") String studentId){
        instituteService.removeStudentFromInstitute(instituteId,studentId);
    }

    // ? Institute Teachers

    @PostMapping("/teachers/add")// -! ADD A TEACHER TO THE INSTITUTE
    public void addTeacher(@RequestBody RegisteredTeachers regTeachers){
        instituteService.addTeacher(regTeachers);
    }
    @DeleteMapping("{instituteId}/teacher/remove/{teacherId}")// -! REMOVE THE TEACHER FORM THE INSTITUTE
    public void removeTeacher(@PathVariable("instituteId") String instituteId,@PathVariable("teacherId") String teacherId){
        instituteService.removeTeacherFromInstitute(instituteId,teacherId);
    }
}
