package com.aegis.controller.organization.response;

import com.aegis.model.Organization;
import com.aegis.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailOrganizationResponse {
    private String id;
    private String name;
    private String description;
    private String createdBy;

    public DetailOrganizationResponse() {
    }

    public DetailOrganizationResponse(Organization entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.createdBy = entity.getCreatedBy() != null ? entity.getCreatedBy().getFullName() : null;
    }
}
