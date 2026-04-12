package dev.pak.insuranceservice.db.repository;

import dev.pak.insuranceservice.db.entity.LifeInsurancePolicy;
import dev.pak.insuranceservice.enums.PolicyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LifeInsurancePolicyRepository extends JpaRepository<LifeInsurancePolicy, UUID> {
    List<LifeInsurancePolicy> findByPolicyStatus(PolicyStatus policyStatus);

    List<LifeInsurancePolicy> findByEndDateBefore(LocalDate endDateBefore);
}
