package com.tubecentric.webapplication.register.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    @Email(message="Must be a valid email")
    @NotBlank(message="Must be a valid email")
    private String email;
}
