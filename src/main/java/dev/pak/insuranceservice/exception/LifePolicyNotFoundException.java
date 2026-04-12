package dev.pak.insuranceservice.exception;

public class LifePolicyNotFoundException extends RuntimeException {
    public LifePolicyNotFoundException(String message) {
        super(message);
    }
}
