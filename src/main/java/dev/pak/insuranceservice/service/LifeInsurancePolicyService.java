package dev.pak.insuranceservice.service;

import dev.pak.insuranceservice.calculator.LifePremiumCalculator;
import dev.pak.insuranceservice.db.entity.LifeInsurancePolicy;
import dev.pak.insuranceservice.db.repository.LifeInsurancePolicyRepository;
import dev.pak.insuranceservice.dto.CreateLifePolicyRequest;
import dev.pak.insuranceservice.dto.CreateLifePolicyResponse;
import dev.pak.insuranceservice.enums.PolicyStatus;
import dev.pak.insuranceservice.exception.InvalidPolicyStatusException;
import dev.pak.insuranceservice.exception.LifePolicyNotFoundException;
import dev.pak.insuranceservice.mapper.LifeInsuranceMapper;
import dev.pak.insuranceservice.validator.LifeInsurancePolicyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * [REQUEST] → Валидация → Расчет премии → Маппинг → Сохранение → [RESPONSE]
 *     ↓            ↓            ↓            ↓          ↓            ↓
 *    JSON     Проверка       BigDecimal    Entity      В БД         DTO
 *             корректности   вычисление    создание    save()       ответ
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LifeInsurancePolicyService {

    private final LifeInsurancePolicyValidator validator;
    private final LifePremiumCalculator calculator;
    private final LifeInsurancePolicyRepository repository;
    private final LifeInsuranceMapper mapper;

    public CreateLifePolicyResponse createPolicy(CreateLifePolicyRequest request) {
        validator.createLifePolicyValidator(request);
        BigDecimal premium = calculator.calculatePremium(request);
        LifeInsurancePolicy policy = mapper.toEntityFromDto(request, premium);
        repository.save(policy);

        return mapper.toResponseFromDto(policy);
    }

    public void activateLifePolicy(UUID policyId) {
        LifeInsurancePolicy policy = repository.findById(policyId).orElseThrow(() -> new LifePolicyNotFoundException("Полис не найден"));
        validator.activateLifePolicyValidator(policy);
        policy.setPolicyStatus(PolicyStatus.ACTIVE);
        policy.setUpdatedAt(LocalDateTime.now());

        repository.save(policy);
    }

    public CreateLifePolicyResponse getById(UUID policyId) {
        return mapper.toResponseFromDto(repository.findById(policyId).orElseThrow(() -> new LifePolicyNotFoundException("Полис не найден")));
    }

    public List<CreateLifePolicyResponse> getPoliciesByStatus(String status) {
        PolicyStatus policyStatus;
        try {
            policyStatus = PolicyStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPolicyStatusException(
                String.format("Некорректный статус полиса: '%s'", status)
            );
        }

        List<LifeInsurancePolicy> policy = repository.findByPolicyStatus(policyStatus);

        return policy.stream()
            .map(mapper::toResponseFromDto)
            .toList();
    }


    public List<CreateLifePolicyResponse> getAllExpiredPolicies() {
        List<LifeInsurancePolicy> policy = repository.findByEndDateBefore(LocalDate.from(LocalDateTime.now()));

        return policy.stream()
            .map(mapper::toResponseFromDto)
            .toList();
    }
}
