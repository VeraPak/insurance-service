package dev.pak.insuranceservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.pak.insuranceservice.enums.PolicyStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateLifePolicyResponse(
    String policyNumber,

    UUID clientExternalId,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate,

    PolicyStatus status,

    BigDecimal premiumAmount,

    BigDecimal coverageAmount,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime createdAt,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime updatedAt,

    int policyTermYears

) {

}
