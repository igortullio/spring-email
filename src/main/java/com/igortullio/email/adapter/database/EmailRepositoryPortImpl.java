package com.igortullio.email.adapter.database;

import com.igortullio.email.core.domain.Email;
import com.igortullio.email.core.port.EmailRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EmailRepositoryPortImpl implements EmailRepositoryPort {

    private final EmailJpaRepository emailRepository;

    @Autowired
    public EmailRepositoryPortImpl(EmailJpaRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Optional<Email> findById(UUID id) {
        return emailRepository.findById(id);
    }

    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
    }

}
