package com.donence.controller;

import javax.validation.Valid;

import com.donence.dto.request.SetAddressForm;
import com.donence.model.User;
import com.donence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * An endpoint for getting user details
     * 
     */
    @GetMapping("/me")
    public ResponseEntity<?> getProfile(){
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok().body(user);
    }

    /**
     * An endpoint for address saving functionality.
     * 
     * @param SetAdressForm required request body including lat-lon double fields.
     */
    @PostMapping("/address")
    public ResponseEntity<?> setAddress(@Valid @RequestBody SetAddressForm setAddressForm) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        user.setAddress(setAddressForm.getLat(), setAddressForm.getLon());
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    /**
     * An endpoint for address saving functionality.
     * 
     * @param SetAdressForm required request body including lat-lon double fields.
     */
    @PutMapping("/address")
    public ResponseEntity<?> changeAddress(@Valid @RequestBody SetAddressForm setAddressForm) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        user.setAddress(setAddressForm.getLat(), setAddressForm.getLon());
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }
}
