package dev.pak.insuranceservice.controller;

import dev.pak.insuranceservice.dto.CreateLifePolicyRequest;
import dev.pak.insuranceservice.dto.CreateLifePolicyResponse;
import dev.pak.insuranceservice.service.LifeInsurancePolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/life-policies")
public class LifeInsuranceController {

    private final LifeInsurancePolicyService service;

    @PostMapping("/create-policy")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateLifePolicyResponse createPolicy(@RequestBody @Valid CreateLifePolicyRequest request) {
        return service.createPolicy(request);
    }

    @PatchMapping("/{policyId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateLifePolicy(@PathVariable UUID policyId) {
        service.activateLifePolicy(policyId);
    }

    @GetMapping("/{policyId}")
    @ResponseStatus(HttpStatus.OK)
    public CreateLifePolicyResponse getById(@PathVariable UUID policyId) {
        return service.getById(policyId);
    }

    @GetMapping("by-status")
    @ResponseStatus(HttpStatus.OK)
    public List<CreateLifePolicyResponse> getPoliciesByStatus(@RequestParam(required = false) String status) {
        return service.getPoliciesByStatus(status);
    }

    @GetMapping("expired-policies")
    @ResponseStatus(HttpStatus.OK)
    public List<CreateLifePolicyResponse> getAllExpiredPolicies() {
        return service.getAllExpiredPolicies();
    }
}
