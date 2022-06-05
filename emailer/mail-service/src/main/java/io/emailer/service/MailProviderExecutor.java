package io.emailer.service;

import io.emailer.dto.MailDto;
import io.emailer.entity.MailProvider;
import org.springframework.lang.NonNull;

public interface MailProviderExecutor {
    void sendMail(MailDto mailDto);
    @NonNull
    MailProvider getMailProvider();
}
