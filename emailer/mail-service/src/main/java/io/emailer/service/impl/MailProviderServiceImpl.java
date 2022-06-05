package io.emailer.service.impl;

import io.emailer.entity.MailProvider;
import io.emailer.entity.MailProviderParam;
import io.emailer.entity.MailProviderParamType;
import io.emailer.repository.MailProviderRepository;
import io.emailer.service.MailProviderExecutor;
import io.emailer.service.MailProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MailProviderServiceImpl implements MailProviderService {

    @Autowired
    private MailProviderRepository mailProviderRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public List<MailProviderExecutor> getMailProviderExecutors() {
        return mailProviderRepository.findAllByOrderByPriority()
                .stream().map(this::convertToExecutor).collect(Collectors.toList());
    }
    
    private MailProviderExecutor convertToExecutor(MailProvider mailProvider) {
        Set<MailProviderParam> providerParams = mailProvider.getParams();
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        for (MailProviderParam providerParam : providerParams) {
            if (providerParam.getType() == MailProviderParamType.HEADER) {
                headers.put(providerParam.getParamKey(), providerParam.getParamValue());
            }
            if (providerParam.getType() == MailProviderParamType.PAYLOAD) {
                payload.put(providerParam.getParamKey(), providerParam.getParamValue());
            }
        }
        HttpMailProviderExecutor mailProviderExecutor = new HttpMailProviderExecutor(mailProvider, restTemplate);
        mailProviderExecutor.setHeaders(headers);
        mailProviderExecutor.setPayload(payload);
        return mailProviderExecutor;
    }
    
}
