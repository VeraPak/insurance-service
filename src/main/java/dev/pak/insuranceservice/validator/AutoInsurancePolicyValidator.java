package dev.pak.insuranceservice.validator;

import dev.pak.insuranceservice.dto.CreateAutoPolicyRequest;
import dev.pak.insuranceservice.exception.InvalidCoverageAmountException;
import dev.pak.insuranceservice.exception.StartDateAfterEndException;
import dev.pak.insuranceservice.exception.StartDateBeforeNowException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class AutoInsurancePolicyValidator {


    public void createAutoPolicyValidator(CreateAutoPolicyRequest request) {
        if (request.startDate().isAfter(request.endDate())) {
            throw new StartDateAfterEndException("Дата начала полиса не может быть позже окончания");
        }
        if (request.startDate().isBefore(LocalDate.now())) {
            throw new StartDateBeforeNowException("Дата начала полиса не может быть раньше текущей");
        }
        if (request.coverageAmount().compareTo(BigDecimal.ZERO) <=0 ) {
            throw new InvalidCoverageAmountException("Сумма страхового покрытия меньше или равна нулю");
        }
    }
}
