package com.igortullio.email.adapter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class EmailDTO {

    private String id;

    private OffsetDateTime dateCreation;

    private OffsetDateTime dateUpdation;

    @NotBlank(message = "{NotBlank}")
    @Email(message = "{Email}")
    private String from;

    @NotEmpty(message = "{NotEmpty}")
    private Set<@Email(message = "{Email}") String> to;

    @NotBlank(message = "{NotBlank}")
    private String subject;

    @NotBlank(message = "{NotBlank}")
    private String text;

    private String status;

}
