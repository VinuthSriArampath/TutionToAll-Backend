package edu.icet.crm.service.impl;

import edu.icet.crm.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("vinuthsriarampth@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        try {
            emailSender.send(message);
        } catch (MailException e) {
            logger.error("Failed to send email", e);
            throw new MessagingException("Failed to send email", e);
        }
    }
    @Override
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        sendHtmlEmail(to,subject,body);
    }
}