package com.aegis.controller.organization.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrganizationRequest {
    private String name;
    private String description;
}
