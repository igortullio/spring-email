package com.igortullio.email.adapter.persistence.entity;

import com.igortullio.email.core.domain.enumeration.EmailStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "EMAIL")
public class EmailEntity extends AbstractEntity implements Serializable {

    @Column(name = "sender", nullable = false)
    private String from;

    @Column(name = "recipient", nullable = false)
    private String to;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailStatus status;

}
