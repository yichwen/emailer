package io.emailer.exception;

public class NoAvailableMailProviderException extends RuntimeException {
    
    public NoAvailableMailProviderException() {
        super();
    }
    
    public NoAvailableMailProviderException(String message) {
        super(message);
    }
    
}
