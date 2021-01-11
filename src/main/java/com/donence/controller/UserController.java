package com.donence.controller;

import com.donence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO controller for user specific functions like show profile, edit user etc.
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
}
