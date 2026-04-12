package dev.pak.insuranceservice.exception;

public class NotValidLifePolicyStatusException extends RuntimeException {
    public NotValidLifePolicyStatusException(String message) {
        super(message);
    }
}
