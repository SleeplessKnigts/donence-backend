package com.donence.controller;

import com.donence.model.User;
import com.donence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * An endpoint to give address save functionality.
     * 
     */
    @PostMapping("/address")
    public ResponseEntity<?> setAddress(){
        System.out.println("Hello there!");
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok().body("Address should be returned here");
    }

}
