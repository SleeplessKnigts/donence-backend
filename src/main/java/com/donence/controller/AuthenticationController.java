package com.donence.controller;

import com.donence.dto.request.RegistrationForm;
import com.donence.model.User;
import com.donence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//TODO this is the controller where all the authentication endpoints should be provided.
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser() {
        return new ResponseEntity("Successful login.", HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationForm registrationForm) {
        User user = new User(registrationForm.getEmail(), registrationForm.getUsername());
        userService.save(user);
        return new ResponseEntity("User registered successfully", HttpStatus.OK);
    }
}
