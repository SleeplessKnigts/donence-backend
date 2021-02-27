package com.donence.controller;

import javax.validation.Valid;

import com.donence.dto.request.RequestForm;
import com.donence.model.Request;
import com.donence.model.User;
import com.donence.service.abstracts.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/request")
public class RequestController {
    
    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestForm requestForm){
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        String requestType = requestForm.getRequestType();
        if(userService.canUserMakeRequest(user, requestType)){
            Request request = userService.createRequest(user, requestType);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have a request already!");
    }

}
