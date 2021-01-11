package com.donence.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

}
