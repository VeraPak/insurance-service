package dev.pak.insuranceservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateAutoPolicyRequest (
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

        @NotNull(message = "Driver age is required")
        Integer driverAge,

        @NotBlank(message = "Vehicle vin is required")
        String vehicleVin,

        @NotBlank(message = "Vehicle model is required")
        String vehicleModel,

        @NotNull(message = "Vehicle year is required")
        Integer vehicleYear,

        @NotNull(message = "Driver experience years is required")
        Integer driverExperienceYears,

        @NotNull(message = "Accident history is required")
        Boolean hasAccidentHistory,

        @NotNull(message = "Engine power hp is required")
        Integer enginePowerHp,

        @NotBlank(message = "Driver license category is required")
        String driverLicenseCategory
)
{
        // Кастомная валидация: endDate должна быть после startDate
        @AssertTrue(message = "End date must be after start date")
        public boolean isEndDateAfterStartDate() {
                return endDate != null && startDate != null && endDate.isAfter(startDate);
        }
}
