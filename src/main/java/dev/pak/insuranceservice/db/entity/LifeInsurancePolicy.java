package dev.pak.insuranceservice.db.entity;

import dev.pak.insuranceservice.enums.PolicyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность страхового полиса жизни.
 */
@Entity
@Table(name = "life_insurance_policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LifeInsurancePolicy {

    /**
     * Уникальный идентификатор полиса (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Номер полиса.
     */
    @Column(name = "policy_number", nullable = false, unique = true)
    private String policyNumber;

    /**
     * Внешний идентификатор клиента.
     */
    @Column(name = "client_external_id", nullable = false)
    private UUID clientExternalId;

    /**
     * Дата начала действия полиса.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * Дата окончания действия полиса.
     */
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /**
     * Статус полиса.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PolicyStatus policyStatus;

    /**
     * Сумма страховой премии.
     */
    @Column(name = "premium_amount", precision = 15, scale = 2)
    private BigDecimal premiumAmount;

    /**
     * Страховая сумма.
     */
    @Column(name = "coverage_amount", precision = 15, scale = 2)
    private BigDecimal coverageAmount;

    /**
     * Дата создания записи.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата последнего обновления.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Возраст застрахованного.
     */
    @Column(name = "insured_age")
    private Integer insuredAge;

    /**
     * Имя выгодоприобретателя.
     */
    @Column(name = "beneficiary_name")
    private String beneficiaryName;

    /**
     * Родственная связь выгодоприобретателя.
     */
    @Column(name = "beneficiary_relation")
    private String beneficiaryRelation;

    /**
     * Срок действия полиса в годах.
     */
    @Column(name = "policy_term_years")
    private Integer policyTermYears;

    /**
     * Признак высокого риска профессии.
     */
    @Column(name = "is_high_risk_occupation")
    private boolean isHighRiskOccupation;

    /**
     * Признак опасных хобби.
     */
    @Column(name = "has_dangerous_hobbies")
    private boolean hasDangerousHobbies;

    /**
     * Тип страхового покрытия.
     */
    @Column(name = "coverage_type")
    private String coverageType;

    /**
     * Метод перед сохранением.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (policyNumber == null) {
            this.policyNumber = "LIFE-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
        }
    }
}