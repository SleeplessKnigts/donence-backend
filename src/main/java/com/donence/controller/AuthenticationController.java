package com.donence.controller;

import com.donence.dto.request.RegistrationForm;
import com.donence.dto.response.LoginResponse;
import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;
import com.donence.security.TokenProvider;
import com.donence.security.services.UserDetailsImpl;
import com.donence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//TODO this is the controller where all the authentication endpoints should be provided.
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenProvider jwtProvider;

    @Autowired
    UserService userService;

    /**
     * This endpoint provide login functionality for mobile devices.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RegistrationForm loginForm,
            HttpServletRequest request) {
        User user = userService.getUserByEmail(loginForm.getEmail());
        UserDetailsImpl userPrincipal;
        if (user != null) { // User is registered before, build the principal
            userPrincipal = UserDetailsImpl.build(user);
        } else { // First login, register user!
            User newUser = new User();
            Role role = userService.findByRole(Roles.ROLE_USER);
            newUser.setAuthProvider(loginForm.getAuthProvider());
            newUser.setFName(loginForm.getName());
            newUser.setEmail(loginForm.getEmail());
            newUser.setImageUrl(loginForm.getImageUrl());
            newUser.setRole(role);
            userService.save(newUser);
            userPrincipal = UserDetailsImpl.build(newUser);
        }
        String accessToken = jwtProvider.createToken(userPrincipal);
        return ResponseEntity.ok(new LoginResponse(accessToken, userPrincipal.getEmail(), userPrincipal.getUsername(),
                userPrincipal.getRole(), userPrincipal.getName()));

    }
}
