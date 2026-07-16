package dev.pak.insuranceservice.config.life;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Map;

@ConfigurationProperties(prefix = "ms.insurance.calculator.life")
@Component
@Getter
@Setter
@Validated
public class LifePremiumCalculatorProperties {
    @NotNull
    private BigDecimal baseRatePerThousand;

    @Valid
    @NestedConfigurationProperty
    private AgeFactor ageFactor;

    @Valid
    @NestedConfigurationProperty
    private PolicyTermFactor policyTermFactor;

    private BigDecimal highRiskOccupationFactor;

    private BigDecimal dangerousHobbiesFactor;

    private Map<String, BigDecimal> paymentFrequencyFactor;
}
