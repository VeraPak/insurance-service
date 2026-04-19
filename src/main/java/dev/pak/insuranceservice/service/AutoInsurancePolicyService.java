package dev.pak.insuranceservice.service;

import dev.pak.insuranceservice.calculator.AutoPremiumCalculator;
import dev.pak.insuranceservice.calculator.LifePremiumCalculator;
import dev.pak.insuranceservice.db.entity.AutoInsurancePolicy;
import dev.pak.insuranceservice.db.repository.AutoInsurancePolicyRepository;
import dev.pak.insuranceservice.dto.CreateAutoPolicyRequest;
import dev.pak.insuranceservice.dto.CreateAutoPolicyResponse;
import dev.pak.insuranceservice.mapper.AutoInsuranceMapper;
import dev.pak.insuranceservice.validator.AutoInsurancePolicyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AutoInsurancePolicyService {

    private final AutoInsurancePolicyRepository repository;
    private final AutoInsurancePolicyValidator validator;
    private final AutoPremiumCalculator calculator;
    private final AutoInsuranceMapper mapper;


    public CreateAutoPolicyResponse createPolicy(CreateAutoPolicyRequest request) {
        validator.createAutoPolicyValidator(request);
        BigDecimal premium = calculator.calculatePremium(request);
        AutoInsurancePolicy policy = mapper.toEntityFromDto(request, premium);
        repository.save(policy);

        return mapper.toResponseFromDto(policy);
    }

}
