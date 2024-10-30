package edu.icet.crm.service.impl;

import edu.icet.crm.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("vinuthsriarampath@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}