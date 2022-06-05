package io.emailer.exception;

public class MailOperationException extends RuntimeException {
    
    public MailOperationException() {
        super();
    }
    
    public MailOperationException(String message) {
        super(message);
    }
    
}
