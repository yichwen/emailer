package io.emailer.exception;

public class MailUndeliverableException extends MailOperationException {
    
    public MailUndeliverableException() {
        super();
    }
    
    public MailUndeliverableException(String message) {
        super(message);
    }
    
}
