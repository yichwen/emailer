package io.emailer.service.impl;

import io.emailer.dto.MailDto;
import io.emailer.entity.MailProvider;
import io.emailer.exception.MailOperationException;
import io.emailer.service.MailProviderExecutor;
import lombok.Getter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class HttpMailProviderExecutor implements MailProviderExecutor {
    
    private RestTemplate restTemplate;
    @Getter
    private MailProvider mailProvider;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private Map<String, Object> payload = new HashMap<>(); 
    
    public HttpMailProviderExecutor(MailProvider mailProvider, RestTemplate restTemplate) {
        this.mailProvider = mailProvider;
        this.restTemplate = restTemplate;
    }
    
    @Override
    public void sendMail(MailDto mailDto) {
        Map<String, Object> httpPayload = new HashMap<>(this.payload);
        httpPayload.putAll(mailDto.toMap());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(payload, httpHeaders);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(mailProvider.getUrl(), requestEntity, String.class);
        } catch (Exception ex) {
            // handle all exception to retry next service provider
            throw new MailOperationException("Something wrong");
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new MailOperationException("failed to send mail");
        }
    }
    
    public void setHeaders(Map<String, String> headers) {
        for (String key: headers.keySet()) {
            httpHeaders.add(key, headers.get(key));
        }
    }
    
    public void setPayload(Map<String, Object> payload) {
        this.payload.putAll(payload);
    }
    
}
