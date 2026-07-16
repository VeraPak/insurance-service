package dev.pak.insuranceservice.exception;

public class StartDateAfterEndException extends RuntimeException {
    public StartDateAfterEndException(String message) {
        super(message);
    }
}
