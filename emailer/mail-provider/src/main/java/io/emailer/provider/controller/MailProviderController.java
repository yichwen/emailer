package io.emailer.provider.controller;

import io.emailer.provider.dto.MailDto;
import io.emailer.provider.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/v1/provider-service")
public class MailProviderController {
    
    @Value("${api-key}")
    private String API_KEY;
    
    @PostMapping
    public R sendMail(@RequestBody MailDto mailDto, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (API_KEY.equals(apiKey)) {
            log.info("Email is sent from {} to {} with message {}", mailDto.getFromEmail(), mailDto.getToEmail(), mailDto.getMessage());
            return R.ok();
        }
        return R.error("Invalid API Key");
    }
    
}
