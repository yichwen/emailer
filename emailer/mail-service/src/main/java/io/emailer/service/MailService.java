package io.emailer.service;

import io.emailer.dto.MailDetailsDto;
import io.emailer.dto.MailDto;
import io.emailer.util.P;
import org.springframework.data.domain.Pageable;

public interface MailService {
    void sendMail(MailDto mailDto);
    P<MailDetailsDto> getMails(Pageable pageable);
}
