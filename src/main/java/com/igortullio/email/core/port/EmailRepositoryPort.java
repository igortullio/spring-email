package com.igortullio.email.core.port;

import com.igortullio.email.core.domain.Email;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepositoryPort {

    Optional<Email> findById(UUID id);
    Email save(Email email);

}
