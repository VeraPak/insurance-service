package dev.pak.insuranceservice.exception;

public class AgeValidationException extends RuntimeException {
    public AgeValidationException(String message) {
        super(message);
    }
}
