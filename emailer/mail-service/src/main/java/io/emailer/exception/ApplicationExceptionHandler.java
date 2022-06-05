package io.emailer.exception;

import io.emailer.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoAvailableMailProviderException.class)
    public R handleException(NoAvailableMailProviderException exception) {
        exception.printStackTrace();
        return R.error("No available mail provider. Please contact administrator.");
    }
    
    @ExceptionHandler(MailUndeliverableException.class)
    public R handleException(MailUndeliverableException exception) {
        exception.printStackTrace();
        return R.error("Mail undeliverable. Please contact administrator.");
    }
    
    @ExceptionHandler(Exception.class)
    public R handleException(Exception exception) {
        exception.printStackTrace();
        return R.error("System Error. Please contact administrator.");
    }
}
