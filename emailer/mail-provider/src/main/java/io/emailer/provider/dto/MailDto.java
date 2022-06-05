package io.emailer.provider.dto;

import lombok.Data;

@Data
public class MailDto {
    private String fromName;
    private String fromEmail;
    private String toName;
    private String toEmail;
    private String subject;
    private String message;
}
