package dev.pak.insuranceservice.db.repository;

import dev.pak.insuranceservice.db.entity.AutoInsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoInsurancePolicyRepository extends JpaRepository<AutoInsurancePolicy, UUID> {
}
