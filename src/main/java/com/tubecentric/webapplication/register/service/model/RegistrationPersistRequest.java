package com.tubecentric.webapplication.register.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class RegistrationPersistRequest {
    private final String email;
}
