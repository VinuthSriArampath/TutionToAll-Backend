package edu.icet.crm.service;


public interface EmailService {
    void sendInstituteOtpEmail(String to, String subject, String body);
    void sendInstituteRegistrationSuccessful(String to,String subject, String userName,String instituteName);
}
