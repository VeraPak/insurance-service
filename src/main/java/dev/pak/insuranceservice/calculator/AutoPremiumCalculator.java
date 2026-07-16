package dev.pak.insuranceservice.calculator;

import dev.pak.insuranceservice.config.auto.AutoPremiumCalculatorProperties;
import dev.pak.insuranceservice.dto.CreateAutoPolicyRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutoPremiumCalculator {

    private final AutoPremiumCalculatorProperties properties;

    public BigDecimal calculatePremium(CreateAutoPolicyRequest request) {
        log.info("расчет премии для клиента с id {}", request.clientExternalId());

        BigDecimal basePremium = properties.getAutoInsuranceBaseRate();
        BigDecimal experienceFactor = calculateExperienceFactor(request.driverExperienceYears());
        BigDecimal accidentFactor  = calculateAccidentFactor(request.hasAccidentHistory());

        return basePremium
                .multiply(experienceFactor)
                .multiply(accidentFactor);
    }

    private BigDecimal calculateExperienceFactor(Integer driverExperienceYears) {
        return properties.getDriverExperienceFactor().divide(BigDecimal.valueOf(driverExperienceYears), RoundingMode.HALF_UP);
    }

    private BigDecimal calculateAccidentFactor(Boolean accidentHistory) {
        return Boolean.TRUE.equals(accidentHistory)
                ? properties.getClientAccidentFactor()
                : BigDecimal.ONE;
    }
}
