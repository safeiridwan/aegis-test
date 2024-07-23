package com.aegis.service.organization;

import com.aegis.controller.organization.request.CreateOrganizationRequest;
import com.aegis.controller.organization.request.UpdateOrganizationRequest;
import com.aegis.response.ResponseAPI;
import org.springframework.http.ResponseEntity;

public interface OrganizationService {
    ResponseEntity<ResponseAPI> createOrganization(CreateOrganizationRequest request);
    ResponseEntity<ResponseAPI> updateOrganization(String organizationId, UpdateOrganizationRequest request);
    ResponseEntity<ResponseAPI> listOrganization();
    ResponseEntity<ResponseAPI> detailOrganization(String organizationId);
    ResponseEntity<ResponseAPI> deleteOrganization(String organizationId);
}
