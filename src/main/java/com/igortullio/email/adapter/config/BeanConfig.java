package com.igortullio.email.adapter.config;

import com.igortullio.email.core.port.EmailRepositoryPort;
import com.igortullio.email.core.port.EmailSendPort;
import com.igortullio.email.core.service.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public EmailService emailServicePort(EmailRepositoryPort emailRepository, EmailSendPort emailSend) {
        return new EmailService(emailRepository, emailSend);
    }

}
