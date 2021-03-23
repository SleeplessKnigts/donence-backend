package com.donence.dto.request;

import javax.validation.constraints.NotBlank;

import com.donence.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignRoleForm {
    
    @NotBlank
    String email;

    @NotBlank
    String role;

    public AssignRoleForm(String email, String role) {
        this.email = email;
        this.role = role;
    }
    
}
