package dev.pak.insuranceservice.config.auto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "ms.insurance.calculator.auto")
@Component
@Getter
@Setter
@Validated
public class AutoPremiumCalculatorProperties {
    @NotNull
    private BigDecimal autoInsuranceBaseRate;

    @NotNull
    private BigDecimal driverExperienceFactor;

    @NotNull
    private BigDecimal clientAccidentFactor;
}
