package dev.pak.insuranceservice.controller;

import dev.pak.insuranceservice.dto.CreateAutoPolicyRequest;
import dev.pak.insuranceservice.dto.CreateAutoPolicyResponse;
import dev.pak.insuranceservice.service.AutoInsurancePolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auto-policies")
public class AutoInsurancePolicyController {

    private final AutoInsurancePolicyService service;

    @PostMapping("/create-policy")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAutoPolicyResponse createPolicy(@RequestBody @Valid CreateAutoPolicyRequest request) {
        return service.createPolicy(request);
    }
}
