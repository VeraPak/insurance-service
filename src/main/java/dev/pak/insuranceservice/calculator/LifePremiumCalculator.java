package dev.pak.insuranceservice.calculator;

import dev.pak.insuranceservice.config.life.LifePremiumCalculatorProperties;
import dev.pak.insuranceservice.dto.CreateLifePolicyRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class LifePremiumCalculator {

    private final LifePremiumCalculatorProperties properties;

    public BigDecimal calculatePremium(CreateLifePolicyRequest request) {
        log.info("расчет премии для полиса {}", request.policyNumber());

        validate(request);

        BigDecimal basePremium = request.coverageAmount().multiply(properties.getBaseRatePerThousand());
        BigDecimal ageFactor = getAgeFactor(request.insuredAge());
        BigDecimal termFactor = getPolicyTermYears(request.policyTermYears());
        BigDecimal riscOccupation = request.isHighRiskOccupation() ? properties.getHighRiskOccupationFactor() : BigDecimal.ONE;
        BigDecimal dangerousHobbies = request.hasDangerousHobbies() ? properties.getDangerousHobbiesFactor() : BigDecimal.ONE;

        BigDecimal premium = basePremium
            .multiply(ageFactor)
            .multiply(termFactor)
            .multiply(riscOccupation)
            .multiply(dangerousHobbies);

        return premium;
    }

    private BigDecimal getPolicyTermYears(@Min(value = 1, message = "Policy term must be at least 1 year") @Max(value = 30, message = "Policy term must not exceed 30 years") int policyTermYears) {
        if (policyTermYears < 5) {
            return properties.getPolicyTermFactor().getShortTerm();
        } if (policyTermYears < 20) {
            return properties.getPolicyTermFactor().getMediumTerm();
        } else {
            return properties.getPolicyTermFactor().getLongTerm();
        }
    }

    private BigDecimal getAgeFactor(int age) {
        if (age <= 30) {
            return properties.getAgeFactor().getYoung();
        } else if (age <= 50) {
            return properties.getAgeFactor().getMiddle();
        } else if (age <= 65) {
            return properties.getAgeFactor().getSenior();
        } else {
            return properties.getAgeFactor().getElderly();
        }
    }

    private void validate(CreateLifePolicyRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("request is null");
        }

        if (request.coverageAmount() == null || request.coverageAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("coverage must be > 0");
        }

        if (request.insuredAge() <= 0) {
            throw new IllegalArgumentException("age must be > 0");
        }

        if (request.policyTermYears() < 1 || request.policyTermYears() > 30) {
            throw new IllegalArgumentException("term must be 1..30");
        }
    }
}
