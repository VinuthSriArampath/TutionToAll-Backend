package edu.icet.crm.service;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    void sendEmail(String to,String subject,String body) throws MessagingException;
}
