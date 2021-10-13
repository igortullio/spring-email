package com.igortullio.email.adapter.controller;

import com.igortullio.email.adapter.dto.EmailDTO;
import com.igortullio.email.core.domain.Email;
import com.igortullio.email.core.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmailController(EmailService emailService, ModelMapper modelMapper) {
        this.emailService = emailService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmailDTO getEmail(@PathVariable UUID id) {
        Email email = emailService.findOneEmail(id);
        return modelMapper.map(email, EmailDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmailDTO sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Email email = modelMapper.map(emailDTO, Email.class);
        email = emailService.sendEmail(email);

        return modelMapper.map(email, EmailDTO.class);
    }

}
