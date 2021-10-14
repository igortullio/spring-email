package com.igortullio.email.adapter.smtp;

import com.igortullio.email.core.domain.Email;
import com.igortullio.email.core.port.EmailSendPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SmptEmailSend implements EmailSendPort {

    private final JavaMailSender javaMailSender;

    @Autowired
    public SmptEmailSend(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(Email email) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(email.getFrom());
        smm.setTo(email.getTo().split(","));
        smm.setSubject(email.getSubject());
        smm.setText(email.getText());
        smm.setSentDate(new Date(email.getDateCreation().toInstant().toEpochMilli()));

        javaMailSender.send(smm);
    }

}
