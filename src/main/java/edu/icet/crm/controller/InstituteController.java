package edu.icet.crm.controller;

import edu.icet.crm.model.Institute;
import edu.icet.crm.service.EmailService;
import edu.icet.crm.service.InstituteService;
import jakarta.mail.MessagingException;
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
@RequestMapping("/institute")
public class InstituteController {

    final InstituteService instituteService;
    final EmailService emailService;

    Random r=new Random();

    @GetMapping("/searchInstituteById/{id}")
    public Institute searchInstituteBYId(@PathVariable String id){
        return instituteService.getInstituteById(id);
    }

    @PostMapping("/register-institutes")
    public void registerInstitutes(@Valid @RequestBody Institute institute){
        instituteService.registerInstitutes(institute);
    }

    @DeleteMapping("/delete-institute/{id}")
    public void deleteInstitute(@PathVariable String id){
        instituteService.deleteInstitute(id);
    }

    @PatchMapping("/updateStudent")
    public void updateStudent(@Valid @RequestBody Institute institute){
        instituteService.updateInstitute(institute);
    }

    @GetMapping("/getRandomOtp/InstituteEmail/{email}")
    public int getRandomOtp(@PathVariable String email) {

        int randomOtp =r.nextInt(100000, 999999);

        try {
            emailService.sendEmail(email, "Institute Email Verification", "Your Otp Is :- " + randomOtp);
            return randomOtp;
        } catch (MessagingException e) {
            return -1;
        }

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
