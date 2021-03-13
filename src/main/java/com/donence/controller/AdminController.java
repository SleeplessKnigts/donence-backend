package com.donence.controller;

import com.donence.dto.request.RecyclePointDto;
import com.donence.model.RecyclePoint;
import com.donence.service.abstracts.UserService;
import com.donence.service.abstracts.RequestService;
import com.donence.service.abstracts.RecyclePointService;
import com.donence.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    RecyclePointService recyclePointService;

    @Autowired
    RequestService requestService;

    @Autowired
    UserService userService;

    @GetMapping("/recycle-point/")
    public ResponseEntity<List<RecyclePointDto>> getRecyclePoints() {
        return ResponseEntity.ok(recyclePointService.getRecyclePointDtos());
    }

    @PostMapping("/recycle-point/new")
    public ResponseEntity<?> addRecyclePoint(@RequestBody RecyclePointDto recyclePointDto) {
        RecyclePoint recyclePoint = new RecyclePoint();
        recyclePoint.setRecyclePointDetail(recyclePointDto.getRecyclePointDetail());
        recyclePoint.setGeolocation(recyclePointDto.getLat(), recyclePointDto.getLng());
        recyclePointService.add(recyclePoint);
        return ResponseEntity.ok("Recycle point added successfully");
    }

    @GetMapping(value = {"/requests", "/requests/{isActive}"})
    public ResponseEntity<List<Request>> getActiveRequests(@PathVariable Optional<Boolean> isActive) {
        return isActive.map(active -> ResponseEntity.ok(requestService.getRequestsByActiveOrderByCreationDateDesc(active))).orElseGet(() -> ResponseEntity.ok(requestService.getRequestOrderByCreationDateDesc()));
    }

    @GetMapping(value = {"/requests/get-user-request/{userId}", "/requests/get-user-request/{userId}/{isActive}"})
    public ResponseEntity<List<Request>> getRequests(@PathVariable Integer userId, @PathVariable Optional<Boolean> isActive) {
        return isActive.map(active -> ResponseEntity.ok(requestService.getRequestsByActiveAndIssuerOrderByCreationDateDesc(isActive.get(), userService.getUserById(userId)))).orElseGet(() -> ResponseEntity.ok(requestService.getRequestsByIssuerOrderByCreationDateDesc(userService.getUserById(userId))));
    }


}
