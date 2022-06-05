package io.emailer.dto;

import io.emailer.entity.MailStatus;
import lombok.Data;

@Data
public class MailStatusDto {
    private Long id;
    private MailStatus status;
    private Long mailProviderId;
}
