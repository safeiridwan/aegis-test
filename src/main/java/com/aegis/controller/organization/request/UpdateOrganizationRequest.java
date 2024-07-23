package com.aegis.controller.organization.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrganizationRequest {
    private String name;
    private String description;
}
