package dev.pak.insuranceservice.exception;

public class StartDateBeforeNowException extends RuntimeException {
    public StartDateBeforeNowException(String message) {
        super(message);
    }
}