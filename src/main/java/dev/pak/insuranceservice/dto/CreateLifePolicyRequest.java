package dev.pak.insuranceservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateLifePolicyRequest(
    @NotBlank(message = "Policy number is required")
    String policyNumber,

    @NotNull(message = "Client external ID is required")
    UUID clientExternalId,

    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate,

    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate,

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be positive")
    BigDecimal coverageAmount,

    int insuredAge,

    @NotBlank(message = "Beneficiary name is required")
    @Size(max = 255, message = "Beneficiary name is too long")
    String beneficiaryName,

    @NotBlank(message = "Beneficiary relation is required")
    @Size(max = 100, message = "Beneficiary relation is too long")
    String beneficiaryRelation,

    boolean isHighRiskOccupation,

    boolean hasDangerousHobbies,

    @Min(value = 1, message = "Policy term must be at least 1 year")
    @Max(value = 30, message = "Policy term must not exceed 30 years")
    int policyTermYears

) {
    // Кастомная валидация: endDate должна быть после startDate
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateAfterStartDate() {
        return endDate != null && startDate != null && startDate.isBefore(endDate) && startDate.isBefore(LocalDate.now());
    }
}
