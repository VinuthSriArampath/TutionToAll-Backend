package edu.icet.crm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EmailServiceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    EmailService emailService;
    @Test
    void test_sendEmail_whenAllCredentialsAreCorrect() throws MessagingException {
        emailService.sendEmail("vinuthsriarampth@gmail.com","Email From TuitionToAll","Testing email from TuitionToAll");
    }
}
