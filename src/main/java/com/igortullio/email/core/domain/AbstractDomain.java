package com.igortullio.email.core.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class AbstractDomain {

    private UUID id;
    private OffsetDateTime dateCreation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

}
