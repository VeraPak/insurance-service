package dev.pak.insuranceservice.db.entity;

import dev.pak.insuranceservice.enums.PolicyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auto_insurance_policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoInsurancePolicy {
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
     * VIN-номер автомобиля.
     */
    @Size(max = 50)
    @Column(name = "vehicle_vin", length = 50)
    String vehicleVin;

    /**
     * Марка и модель ТС.
     */
    @Size(max = 100)
    @Column(name = "vehicle_model", length = 100)
    String vehicleModel;

    /**
     * Год выпуска ТС.
     */
    @Column(name = "vehicle_year")
    Integer vehicleYear;

    /**
     * Стаж вождения (лет).
     */
    @Min(0)
    @Column(name = "driver_experience_years")
    Integer driverExperienceYears;

    /**
     * Наличие аварий по вине водителя.
     */
    @Column(name = "has_accident_history")
    Boolean hasAccidentHistory;

    /**
     * Мощность двигателя (л.с.).
     */
    @Min(1)
    @Column(name = "engine_power_hp")
    Integer enginePowerHp;

    /**
     * Возраст водителя.
     */
    @Column(name = "driver_age")
    Integer driverAge;

    /**
     * Категория прав.
     */
    @Column(name = "driver_license_category")
    String driverLicenseCategory;

    /**
     * Метод перед сохранением.
     */
    @PrePersist
    protected void onCreate() {
        generateDefaultPolicyNumber();
        setupCreationAndUpdateDate();
    }

    /**
     * Метод при обновлении.
     */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Метод установки дефолтного номера полиса, если не передан.
     */
    private void generateDefaultPolicyNumber() {
        if (policyNumber == null) {
            this.policyNumber = "AUTO-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
        }
    }

    /**
     * Метод установки времени создания полиса.
     */
    private void setupCreationAndUpdateDate() {
        LocalDateTime creationDate = LocalDateTime.now();
        this.createdAt = creationDate;
        this.updatedAt = creationDate;
    }


}
