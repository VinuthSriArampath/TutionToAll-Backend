package edu.icet.crm.service.impl;

import edu.icet.crm.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    public void sendHtmlEmail(String to, String subject, String templateName, Map<String, Object> templateModel) throws MessagingException {
        Context context = new Context();
        context.setVariables(templateModel);
        String htmlBody = templateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("vinuthsriarampth@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send email", e);
        }
    }
    @Override
    public void sendInstituteOtpEmail(String to, String subject, String body) {

        try {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("otp", body);

            sendHtmlEmail(to, subject, "InstituteOtpMail", templateModel);

        } catch (MessagingException e) {
            log.error("Failed to send email", e);
        }
    }

    @Override
    public void sendInstituteRegistrationSuccessful(String to, String subject, String userName,String instituteName) {
        try {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("userName", userName);
            templateModel.put("instituteName",instituteName);

            sendHtmlEmail(to, subject, "InstituteRegisteredSuccessfullyMail", templateModel);

        } catch (MessagingException e) {
            log.error("Failed to send email in institute registration", e);
        }
    }

    @Override
    public void sentTeacherRegistrationSuccessful(String to, String subject, String userName) {
        try {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("userName", userName);

            sendHtmlEmail(to, subject, "TeacherRegisteredSuccessfullyMail", templateModel);

        } catch (MessagingException e) {
            log.error("Failed to send email in teacher registration", e);
        }
    }

    @Override
    public void sentStudentRegistrationSuccessful(String to, String subject, String userName) {
        try {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("userName", userName);

            sendHtmlEmail(to, subject, "StudentRegisteredSuccessfullyMail", templateModel);

        } catch (MessagingException e) {
            log.error("Failed to send email", e);
        }
    }
}