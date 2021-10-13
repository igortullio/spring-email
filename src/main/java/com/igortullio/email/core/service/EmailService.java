package com.igortullio.email.core.service;

import com.igortullio.email.core.domain.Email;
import com.igortullio.email.core.domain.enumeration.EmailStatus;
import com.igortullio.email.core.exception.NotFoundException;
import com.igortullio.email.core.port.EmailRepositoryPort;
import com.igortullio.email.core.port.EmailSendPort;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EmailService(EmailRepositoryPort emailRepository,
                           EmailSendPort emailSend) {

    public Email findOneEmail(UUID id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Email.class, id));
    }

    public Email sendEmail(Email email) {
        email.setDateCreation(OffsetDateTime.now());

        try {
            emailSend.send(email);
            email.setStatus(EmailStatus.SENT);
        } catch (Exception exception) {
            email.setStatus(EmailStatus.ERROR);
        }

        return emailRepository.save(email);
    }

}
