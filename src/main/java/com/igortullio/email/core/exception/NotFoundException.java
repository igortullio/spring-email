package com.igortullio.email.core.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz, UUID id) {
        super(clazz.getSimpleName() + " not found with id: " + id);
    }

}
