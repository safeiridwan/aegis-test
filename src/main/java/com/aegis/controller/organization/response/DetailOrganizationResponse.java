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

    public DetailOrganizationResponse() {
    }

    public DetailOrganizationResponse(Organization entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
