package com.tubecentric.webapplication.web.landing.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SubscribeForm {

    @Email(message="Must be a valid email")
    @NotBlank(message="Must not be empty")
    private String email;
}
