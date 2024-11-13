package edu.icet.crm.controller;

import edu.icet.crm.model.Institute;
import edu.icet.crm.model.RegisteredStudents;
import edu.icet.crm.model.RegisteredTeachers;
import edu.icet.crm.service.EmailService;
import edu.icet.crm.service.InstituteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/institutes")
public class InstituteController {
    private final InstituteService instituteService;
    private final EmailService emailService;
    private final Random random = new Random();

    @GetMapping("/search/{id}")
    public Institute searchInstituteById(@PathVariable String id) {
        return instituteService.getInstituteById(id);
    }

    @PostMapping("/register")
    public void registerInstitute(@Valid @RequestBody Institute institute) {
        instituteService.registerInstitutes(institute);
    }

    @DeleteMapping("/{id}")
    public void deleteInstitute(@PathVariable String id) {
        instituteService.deleteInstitute(id);
    }

    @PatchMapping("/update")
    public void updateInstitute(@RequestBody Institute institute) {
        instituteService.updateInstitute(institute);
    }

    @GetMapping("/otp/{email}")
    public int generateOtp(@PathVariable String email) {
        int otp = random.nextInt(100000, 999999);
        emailService.sendInstituteOtpEmail(email, "Institute Email Verification",String.valueOf(otp));
        return otp;
    }
    @GetMapping("/all")
    public List<Institute> getAllInstitutes(){
        return instituteService.getAllInstitute();
    }
    @PostMapping("/students/add")
    public void addStudent(@RequestBody RegisteredStudents regStudents){
        instituteService.addStudent(regStudents);
    }
    @DeleteMapping("{instituteId}/students/remove/{studentId}")
    public void removeStudent(@PathVariable("instituteId") String instituteId,@PathVariable("studentId") String studentId){
        instituteService.removeStudentFromInstitute(instituteId,studentId);
    }
    @DeleteMapping("{instituteId}/teacher/remove/{teacherId}")
    public void removeTeacher(@PathVariable("instituteId") String instituteId,@PathVariable("teacherId") String teacherId){
        instituteService.removeTeacherFromInstitute(instituteId,teacherId);
    }

    @PostMapping("/teachers/add")
    public void addTeacher(@RequestBody RegisteredTeachers regTeachers){
        instituteService.addTeacher(regTeachers);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
