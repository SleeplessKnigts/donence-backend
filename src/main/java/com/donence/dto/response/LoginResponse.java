package com.donence.dto.response;

import com.donence.model.Role;

import lombok.Data;

@Data
public class LoginResponse {

    private String accessToken;

    private String email;

    private Role role;

    private String username;

    private String name;

    public LoginResponse(String accessToken,String email, String username, Role role, String name){
        this.accessToken = accessToken;
        this.email = email;
        this.username = username;
        this.role = role;
        this.name = name;
    }

}
