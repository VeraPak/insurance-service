package dev.pak.insuranceservice.mapper;

import dev.pak.insuranceservice.db.entity.AutoInsurancePolicy;
import dev.pak.insuranceservice.dto.CreateAutoPolicyRequest;
import dev.pak.insuranceservice.dto.CreateAutoPolicyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AutoInsuranceMapper {
    String DRAFT = "DRAFT";

    @Mapping(target = "clientExternalId", source = "request.clientExternalId")
    @Mapping(target = "startDate", source = "request.startDate")
    @Mapping(target = "endDate", source = "request.endDate")
    @Mapping(target = "coverageAmount", source = "request.coverageAmount")
    @Mapping(target = "driverAge", source = "request.driverAge")
    @Mapping(target = "vehicleVin", source = "request.vehicleVin")
    @Mapping(target = "vehicleModel", source = "request.vehicleModel")
    @Mapping(target = "vehicleYear", source = "request.vehicleYear")
    @Mapping(target = "driverExperienceYears", source = "request.driverExperienceYears")
    @Mapping(target = "hasAccidentHistory", source = "request.hasAccidentHistory")
    @Mapping(target = "enginePowerHp", source = "request.enginePowerHp")
    @Mapping(target = "driverLicenseCategory", source = "request.driverLicenseCategory")
    @Mapping(target = "policyStatus", constant = DRAFT)
    @Mapping(target = "premiumAmount", source = "premium")
    AutoInsurancePolicy toEntityFromDto(CreateAutoPolicyRequest request, BigDecimal premium);

    @Mapping(target = "id", source = "policy.id")
    @Mapping(target = "policyNumber", source = "policy.policyNumber")
    @Mapping(target = "clientExternalId", source = "policy.clientExternalId")
    @Mapping(target = "startDate", source = "policy.startDate")
    @Mapping(target = "endDate", source = "policy.endDate")
    @Mapping(target = "policyStatus", source = "policy.policyStatus")
    @Mapping(target = "premiumAmount", source = "policy.premiumAmount")
    @Mapping(target = "coverageAmount", source = "policy.coverageAmount")
    @Mapping(target = "createdAt", source = "policy.createdAt")
    @Mapping(target = "updatedAt", source = "policy.updatedAt")
    CreateAutoPolicyResponse toResponseFromDto(AutoInsurancePolicy policy);

}
