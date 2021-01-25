package com.donence.controller;

import com.donence.dto.request.RecyclePointDto;
import com.donence.model.RecyclePoint;
import com.donence.service.RecyclePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    RecyclePointService recyclePointService;

    @GetMapping("/recycle-point/")
    public ResponseEntity<?> getRecyclePoints(){
        return null;
    }

    @PostMapping("/recycle-point/new")
    public ResponseEntity<?> addRecyclePoint(@Valid @RequestBody RecyclePointDto recyclePointDto){
        RecyclePoint recyclePoint = new RecyclePoint();
        recyclePoint.setRecylePointName(recyclePointDto.getRecyclePointName());
        recyclePoint.setGeolocation(recyclePointDto.getGeolocation());
        recyclePointService.add(recyclePoint);
        return ResponseEntity.ok("Recycle point added successfully");
    }

}
