package io.emailer.dto;

import io.emailer.entity.MailStatus;
import lombok.Data;

@Data
public class MailDetailsDto extends MailDto {
    private Long id;
    private MailStatus status;
    private String mailProvider;
}
