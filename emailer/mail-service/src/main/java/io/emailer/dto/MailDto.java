package io.emailer.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MailDto {
    private String fromName;
    private String fromEmail;
    private String toName;
    private String toEmail;
    private String subject;
    private String message;
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("fromName", fromName);
        map.put("fromEmail", fromEmail);
        map.put("toName", toName);
        map.put("toEmail", toEmail);
        map.put("message", message);
        return map;
    }
    
}
