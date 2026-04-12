package dev.pak.insuranceservice.exception.errorDto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto (
    String header,
    String message,
    LocalDateTime timestamp
){
}
