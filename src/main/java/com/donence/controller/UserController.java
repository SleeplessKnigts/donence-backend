package com.donence.controller;

import java.util.List;

import javax.validation.Valid;

import com.donence.dto.request.SetAddressForm;
import com.donence.dto.response.UserDetailResponse;
import com.donence.model.Request;
import com.donence.model.User;
import com.donence.service.abstracts.RequestService;
import com.donence.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RequestService requestService;

    /**
     * An endpoint for getting user details
     * 
     */
    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        UserDetailResponse response = new UserDetailResponse(user);

        return ResponseEntity.ok().body(response);
    }

    /**
     * An endpoint for address saving functionality.
     * 
     * @param setAddressForm required request body including lat-lon double fields.
     */

    @PostMapping("/address")
    public ResponseEntity<?> setAddress(@Valid @RequestBody SetAddressForm setAddressForm) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        user.setAddress(setAddressForm.getLat(), setAddressForm.getLon());
        user.setAddressDetail(setAddressForm.getSubAdminArea(), setAddressForm.getSubLocality(),
                setAddressForm.getThoroughfare(), setAddressForm.getPostalCode());
        userService.save(user);
        return ResponseEntity.ok().body("");
    }

    /**
     * An endpoint for address saving functionality.
     * 
     * @param setAddressForm required request body including lat-lon double fields.
     */
    @PutMapping("/address")
    public ResponseEntity<?> changeAddress(@Valid @RequestBody SetAddressForm setAddressForm) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        user.setAddress(setAddressForm.getLat(), setAddressForm.getLon());
        user.setAddressDetail(setAddressForm.getSubAdminArea(), setAddressForm.getSubLocality(),
                setAddressForm.getThoroughfare(), setAddressForm.getPostalCode());
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/requests/{status}")
    public ResponseEntity<?> getRequestsByStatus(@PathVariable String status) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        List<Request> requests;
        switch (status) {
        case "all":
            requests = userService.getRequestsOfUser(user);
            break;
        case "active":
            requests = userService.getActiveRequestsOfUser(user);
            break;
        case "completed":
            requests = userService.getNonActiveRequestOfUser(user);
            break;
        default:
            requests = null;
            break;
        }
        return ResponseEntity.ok().body(requests);
    }

    @GetMapping("/requests/{type}/{status}")
    public ResponseEntity<?> getRequestsByType(@PathVariable String type, @PathVariable String status) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        List<Request> requests;
        switch (status) {
        case "all":
            requests = userService.getRequestsOfUserFilteredByTypeAndStatus(user, type, true);
            requests.addAll(userService.getRequestsOfUserFilteredByTypeAndStatus(user, type, false));
            break;
        case "active":
            requests = userService.getRequestsOfUserFilteredByTypeAndStatus(user, type, true);
            break;
        case "completed":
            requests = userService.getRequestsOfUserFilteredByTypeAndStatus(user, type, false);
            break;
        default:
            requests = null;
            break;
        }
        return ResponseEntity.ok().body(requests);
    }

    @GetMapping("requests/canI/{type}")
    public ResponseEntity<?> canUserMakeRequest(@PathVariable String type) {
        User user = userService.getUserByAuthentication(SecurityContextHolder.getContext().getAuthentication());
        boolean canThee = userService.canUserMakeRequest(user, type);
        return canThee ? ResponseEntity.ok().body("Go on then!")
                : ResponseEntity.badRequest().body("You have request already");
    }
}
