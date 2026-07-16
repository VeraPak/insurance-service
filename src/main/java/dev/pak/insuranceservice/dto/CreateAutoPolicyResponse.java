package dev.pak.insuranceservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.pak.insuranceservice.enums.PolicyStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAutoPolicyResponse (
        UUID id,
        String policyNumber,
        UUID clientExternalId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        PolicyStatus policyStatus,
        BigDecimal premiumAmount,
        BigDecimal coverageAmount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
