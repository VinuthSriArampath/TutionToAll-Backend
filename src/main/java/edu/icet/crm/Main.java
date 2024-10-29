package edu.icet.crm;

import edu.icet.crm.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {
    private EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}