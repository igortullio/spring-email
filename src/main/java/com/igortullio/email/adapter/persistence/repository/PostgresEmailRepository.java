package com.igortullio.email.adapter.persistence.repository;

import com.igortullio.email.adapter.persistence.entity.EmailEntity;
import com.igortullio.email.core.domain.Email;
import com.igortullio.email.core.port.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresEmailRepository implements EmailRepositoryPort {

    private final SpringDataPostgresEmailRepository emailRepository;
    private final ModelMapper modelMapper;

    public PostgresEmailRepository(SpringDataPostgresEmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Email> findById(UUID id) {
        Optional<EmailEntity> optionalEmailEntity = emailRepository.findById(id);

        if (optionalEmailEntity.isPresent()) {
            EmailEntity emailEntity = optionalEmailEntity.get();
            return Optional.of(modelMapper.map(emailEntity, Email.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Email save(Email email) {
        EmailEntity emailEntity = modelMapper.map(email, EmailEntity.class);
        return modelMapper.map(emailRepository.save(emailEntity), Email.class);
    }

}
