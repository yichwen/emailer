package io.emailer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mail")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromName;
    private String fromEmail;
    private String toName;
    private String toEmail;
    private String subject;
    private String message;
    @Enumerated(EnumType.STRING)
    private MailStatus status;
    @ManyToOne
    private MailProvider mailProvider;
}
