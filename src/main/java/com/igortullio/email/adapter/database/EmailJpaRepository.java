package com.igortullio.email.adapter.database;

import com.igortullio.email.core.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailJpaRepository extends JpaRepository<Email, UUID> {
}
