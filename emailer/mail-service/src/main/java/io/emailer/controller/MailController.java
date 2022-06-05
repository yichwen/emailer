package io.emailer.controller;

import io.emailer.dto.MailDetailsDto;
import io.emailer.dto.MailDto;
import io.emailer.service.MailService;
import io.emailer.util.P;
import io.emailer.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/mails")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @PostMapping
    public R sendMail(@RequestBody MailDto mailDto) {
        mailService.sendMail(mailDto);
        return R.ok();
    }
    
    @GetMapping
    public R getMails(@RequestParam(value = "page", defaultValue = "0") int page, 
                      @RequestParam(value = "size", defaultValue = "10") int size) {
        P<MailDetailsDto> mails = mailService.getMails(PageRequest.of(page, size));
        return R.ok().data(mails);
    }
    
}
