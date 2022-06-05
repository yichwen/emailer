package io.emailer.service;

import java.util.List;

public interface MailProviderService {
    List<MailProviderExecutor> getMailProviderExecutors();
}
