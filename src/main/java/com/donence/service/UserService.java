package com.donence.service;

import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;

import org.springframework.security.core.Authentication;

public interface UserService {
    User save(User user);

    User getUserById(Integer userId);

    Role findByRole(Roles role);

    User getUserByEmail(String email);

    User getUserByAuthentication(Authentication authentication);
}
