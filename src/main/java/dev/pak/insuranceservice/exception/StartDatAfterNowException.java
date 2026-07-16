package dev.pak.insuranceservice.exception;

public class StartDatAfterNowException extends RuntimeException {
    public StartDatAfterNowException(String message) {
        super(message);
    }
}
