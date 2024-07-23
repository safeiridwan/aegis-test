package com.aegis.controller.organization;

import com.aegis.controller.organization.request.CreateOrganizationRequest;
import com.aegis.controller.organization.request.UpdateOrganizationRequest;
import com.aegis.response.ResponseAPI;
import com.aegis.service.organization.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("organization")
public class OrganizationController {
    private final OrganizationService service;

    @PostMapping()
    public ResponseEntity<ResponseAPI> createOrganization(@Valid @RequestBody CreateOrganizationRequest request) {
        return service.createOrganization(request);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<ResponseAPI> updateOrganization(
            @PathVariable(name = "organizationId") String organizationId,
            @Valid @RequestBody UpdateOrganizationRequest request) {
        return service.updateOrganization(organizationId, request);
    }

    @GetMapping()
    public ResponseEntity<ResponseAPI> listOrganization() {
        return service.listOrganization();
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<ResponseAPI> detailOrganization(@PathVariable(name = "organizationId") String organizationId) {
        return service.detailOrganization(organizationId);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<ResponseAPI> deleteOrganization(@PathVariable(name = "organizationId") String organizationId) {
        return service.deleteOrganization(organizationId);
    }
}
