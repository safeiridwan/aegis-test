package com.aegis.service.organization;

import com.aegis.controller.organization.request.CreateOrganizationRequest;
import com.aegis.controller.organization.request.UpdateOrganizationRequest;
import com.aegis.controller.organization.response.DetailOrganizationResponse;
import com.aegis.model.Organization;
import com.aegis.model.User;
import com.aegis.repository.OrganizationRepository;
import com.aegis.repository.UserRepository;
import com.aegis.response.ResponseAPI;
import com.aegis.util.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.aegis.util.constant.ResponseMessage.*;
import static com.aegis.util.constant.Constant.*;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    @Override
    public ResponseEntity<ResponseAPI> createOrganization(CreateOrganizationRequest request) {
        String userId = authenticationFacade.getUserId();
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(403, UNAUTHORIZED_ERROR, null, null), HttpStatus.UNAUTHORIZED);
        }

        Organization organization = new Organization();
        organization.setId(UUID.randomUUID().toString());
        organization.setName(request.getName());
        organization.setDescription(request.getDescription());
        organization.setCreatedBy(user);

        organizationRepository.save(organization);
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailOrganizationResponse(organization)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> updateOrganization(String organizationId, UpdateOrganizationRequest request) {
        String userId = authenticationFacade.getUserId();
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(403, UNAUTHORIZED_ERROR, null, null), HttpStatus.UNAUTHORIZED);
        }

        Organization organization = organizationRepository.findById(organizationId);
        if (organization == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "Organization not found", null, null), HttpStatus.BAD_REQUEST);
        }

        if (user.getRole().equals(USER_ROLE) && !organization.getCreatedBy().getUserId().equals(userId)) {
            return new ResponseEntity<>(new ResponseAPI(403, REQUEST_FORBIDDEN_ERROR, null, null), HttpStatus.FORBIDDEN);
        }

        organization.setName(request.getName());
        organization.setDescription(request.getDescription());

        organizationRepository.save(organization);
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailOrganizationResponse(organization)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> listOrganization() {
        List<DetailOrganizationResponse> res = organizationRepository.findAll()
                .stream()
                .map(DetailOrganizationResponse::new)
                .toList();
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, res), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> detailOrganization(String organizationId) {
        Organization organization = organizationRepository.findById(organizationId);
        if (organization == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "Organization not found", null, null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailOrganizationResponse(organization)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> deleteOrganization(String organizationId) {
        String userId = authenticationFacade.getUserId();
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(403, UNAUTHORIZED_ERROR, null, null), HttpStatus.UNAUTHORIZED);
        }

        Organization organization = organizationRepository.findById(organizationId);
        if (organization == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "Organization not found.", null, null), HttpStatus.BAD_REQUEST);
        }

        if (user.getRole().equals(USER_ROLE) && !organization.getCreatedBy().getUserId().equals(userId)) {
            return new ResponseEntity<>(new ResponseAPI(403, REQUEST_FORBIDDEN_ERROR, null, null), HttpStatus.FORBIDDEN);
        }

        organizationRepository.delete(organization);
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, null), HttpStatus.OK);
    }
}
