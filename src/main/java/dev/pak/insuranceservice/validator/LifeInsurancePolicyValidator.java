package dev.pak.insuranceservice.validator;

import dev.pak.insuranceservice.db.entity.LifeInsurancePolicy;
import dev.pak.insuranceservice.dto.CreateLifePolicyRequest;
import dev.pak.insuranceservice.enums.PolicyStatus;
import dev.pak.insuranceservice.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class LifeInsurancePolicyValidator {

    public void createLifePolicyValidator(CreateLifePolicyRequest request) {
        if (request.policyNumber() == null) {
            throw new PolicyNumberException("Номер полиса не может быть пустым");
        }
        if (request.startDate().isAfter(request.endDate())) {
            throw new DateValidationException("Дата начала полиса не может быть позже окончания");
        }
        if (request.insuredAge() < 18) {
            throw new AgeValidationException("Застрахованное лицо не может быть младше 18 лет");
        }
        if (request.policyTermYears() <= 0 ) {
            throw new TermYearsException("Срок полиса не может быть отрицательным");
        }
    }

    public void activateLifePolicyValidator(LifeInsurancePolicy policy) {
        if (policy.getPolicyStatus() != PolicyStatus.DRAFT) {
            throw new NotValidLifePolicyStatusException("Недопустимый статус");
        }
        if (policy.getStartDate().isAfter(LocalDate.now())) {
            throw new StartDatAfterNowException("Дата начала в будущем");
        }
    }
}
