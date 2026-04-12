package dev.pak.insuranceservice.calculator;

import dev.pak.insuranceservice.config.life.AgeFactor;
import dev.pak.insuranceservice.config.life.LifePremiumCalculatorProperties;
import dev.pak.insuranceservice.config.life.PolicyTermFactor;
import dev.pak.insuranceservice.dto.CreateLifePolicyRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LifePremiumCalculatorTest {

    @InjectMocks
    LifePremiumCalculator calculator;
    @Mock
    LifePremiumCalculatorProperties properties;

    @BeforeEach
    void setUp() {
        AgeFactor ageFactor = mock(AgeFactor.class);
        PolicyTermFactor termFactor = mock(PolicyTermFactor.class);

        when(ageFactor.getYoung()).thenReturn(BigDecimal.valueOf(1.2));
        when(ageFactor.getMiddle()).thenReturn(BigDecimal.valueOf(1.0));
        when(ageFactor.getSenior()).thenReturn(BigDecimal.valueOf(1.8));
        when(ageFactor.getElderly()).thenReturn(BigDecimal.valueOf(2.5));

        when(termFactor.getShortTerm()).thenReturn(BigDecimal.valueOf(1.1));
        when(termFactor.getMediumTerm()).thenReturn(BigDecimal.valueOf(1.0));
        when(termFactor.getLongTerm()).thenReturn(BigDecimal.valueOf(0.9));

        when(properties.getAgeFactor()).thenReturn(ageFactor);
        when(properties.getPolicyTermFactor()).thenReturn(termFactor);

        when(properties.getBaseRatePerThousand()).thenReturn(BigDecimal.valueOf(1.5));
        when(properties.getHighRiskOccupationFactor()).thenReturn(BigDecimal.valueOf(1.5));
        when(properties.getDangerousHobbiesFactor()).thenReturn(BigDecimal.valueOf(1.5));
    }


    @MethodSource
    @ParameterizedTest
    void calculatePremium_positiveTest(
            int coverage,
            int age,
            int term,
            boolean riskJob,
            boolean hobbies,
            BigDecimal expected
    ) {

        // given
        CreateLifePolicyRequest request = new CreateLifePolicyRequest(
                "policy-number-1",
                UUID.randomUUID(),
                LocalDate.of(2025, 11, 21),
                LocalDate.of(2026, 11, 21),
                BigDecimal.valueOf(coverage),
                age,
                "John",
                "Wife",
                riskJob,
                hobbies,
                term
        );

        // when
        BigDecimal premium = calculator.calculatePremium(request);

        //then
        Assertions.assertThat(premium).isEqualByComparingTo(expected);
    }

    @MethodSource
    @ParameterizedTest
    void calculatePremium_negativeTest(
            BigDecimal coverage,
            Integer age,
            Integer term,
            boolean riskJob,
            boolean hobbies
    ) {
        CreateLifePolicyRequest request = new CreateLifePolicyRequest(
                "policy-number-1",
                UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                coverage,
                age,
                "John",
                "Wife",
                riskJob,
                hobbies,
                term
        );

        Assertions.assertThatThrownBy(() -> calculator.calculatePremium(request))
                .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> calculatePremium_positiveTest() {
        return Stream.of(
                Arguments.of(10, 22, 5, false, false, BigDecimal.valueOf(18.0)),
                Arguments.of(10, 25, 3, false, false, BigDecimal.valueOf(19.8)),
                Arguments.of(10, 40, 10, false, false, BigDecimal.valueOf(15.0)),
                Arguments.of(10, 60, 25, false, false, BigDecimal.valueOf(24.3)),
                Arguments.of(10, 70, 25, false, false, BigDecimal.valueOf(33.75)),
                Arguments.of(10, 30, 10, true, true, BigDecimal.valueOf(40.5))
        );
    }

    static Stream<Arguments> calculatePremium_negativeTest() {
        return Stream.of(
                Arguments.of(null, 25, 10, false, false),
                Arguments.of(BigDecimal.valueOf(-10), 25, 10, false, false),
                Arguments.of(BigDecimal.TEN, 0, 10, false, false),
                Arguments.of(BigDecimal.TEN, 25, 0, false, false),
                Arguments.of(BigDecimal.TEN, 25, 100, false, false)
        );
    }

}