package dev.pak.insuranceservice.config.life;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PolicyTermFactor {

    @NotNull
    private BigDecimal shortTerm;

    @NotNull
    private BigDecimal mediumTerm;

    @NotNull
    private BigDecimal longTerm;

}
