package dev.pak.insuranceservice.exception;

public class InvalidCoverageAmountException extends RuntimeException {
    public InvalidCoverageAmountException(String message) {
        super(message);
    }
}
