package dev.pak.insuranceservice.exception;

import dev.pak.insuranceservice.exception.errorDto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LifePolicyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleLifePolicyNotFoundException() {
        return ErrorResponseDto.builder()
            .header("Полис с указанным id не найден в системе.")
            .message("Полис с указанным id не найден в системе. Обратитесь в службу поддержки.")
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(NotValidLifePolicyStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleNotValidLifePolicyStatusException() {
        return ErrorResponseDto.builder()
            .build();
    }

    @ExceptionHandler(StartDatAfterNowException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleStartDatAfterNowException() {
        return ErrorResponseDto.builder()
            .header("Дата начала полиса позже текущей даты.")
            .message("Дата начала полиса позже текущей даты. Обратитесь в службу поддержки.")
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(InvalidPolicyStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleInvalidPolicyStatusException() {
        return ErrorResponseDto.builder()
            .message(String.format("Некорректный статус полиса"))
            .timestamp(LocalDateTime.now())
            .build();
    }
}
