package com.donence.controller;

import com.donence.dto.request.AssignRoleForm;
import com.donence.dto.request.CollectionEventDto;
import com.donence.dto.request.NewForm;
import com.donence.dto.request.RecyclePointDto;
import com.donence.model.*;
import com.donence.service.FirebaseMessagingService;
import com.donence.service.abstracts.*;
import com.google.firebase.messaging.FirebaseMessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @Autowired
    RecyclePointService recyclePointService;

    @Autowired
    RequestService requestService;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    CollectionEventService collectionEventService;

    @GetMapping("/recycle-point/")
    public ResponseEntity<List<RecyclePointDto>> getRecyclePoints() {
        return ResponseEntity.ok(recyclePointService.getRecyclePointDtos());
    }

    @PostMapping("/recycle-point/new")
    public ResponseEntity<?> addRecyclePoint(@RequestBody RecyclePointDto recyclePointDto) {
        RecyclePoint recyclePoint = new RecyclePoint();
        recyclePoint.setRecyclePointDetail(recyclePointDto.getRecyclePointDetail());
        recyclePoint.setRecyclePointPlaceType(recyclePointDto.getRecyclePointPlaceType());
        recyclePoint.setGeolocation(recyclePointDto.getLat(), recyclePointDto.getLng());
        recyclePointService.add(recyclePoint);
        return ResponseEntity.ok("Recycle point added successfully");
    }

    @DeleteMapping("/recycle-point/delete/{recyclePointId}")
    public ResponseEntity<?> deleteRecyclePoint(@PathVariable Integer recyclePointId) {
        recyclePointService.delete(recyclePointService.getPointById(recyclePointId));
        return ResponseEntity.ok("Recycle point deleted successfully");
    }

    @PutMapping("/recycle-point/update")
    public ResponseEntity<?> updateRecyclePoint(@RequestBody RecyclePointDto recyclePointDto) {
        RecyclePoint recyclePoint = recyclePointService.getPointById(recyclePointDto.getRecyclePointId());
        recyclePoint.setRecyclePointDetail(recyclePointDto.getRecyclePointDetail());
        recyclePoint.setRecyclePointPlaceType(recyclePointDto.getRecyclePointPlaceType());
        recyclePoint.setGeolocation(recyclePointDto.getLat(), recyclePointDto.getLng());
        recyclePointService.add(recyclePoint);
        return ResponseEntity.ok("Recycle point updated successfully");
    }

    @GetMapping(value = { "/requests", "/requests/{isActive}" })
    public ResponseEntity<List<Request>> getRequests(@PathVariable Optional<Boolean> isActive) {
        return isActive
                .map(active -> ResponseEntity.ok(requestService.getRequestsByActiveOrderByCreationDateDesc(active)))
                .orElseGet(() -> ResponseEntity.ok(requestService.getRequestOrderByCreationDateDesc()));
    }

    @GetMapping(value = { "/requests/get-user-request/{userId}", "/requests/get-user-request/{userId}/{isActive}" })
    public ResponseEntity<List<Request>> getUserRequests(@PathVariable Integer userId,
            @PathVariable Optional<Boolean> isActive) {
        return isActive
                .map(active -> ResponseEntity.ok(requestService.getRequestsByActiveAndIssuerOrderByCreationDateDesc(
                        isActive.get(), userService.getUserById(userId))))
                .orElseGet(() -> ResponseEntity.ok(
                        requestService.getRequestsByIssuerOrderByCreationDateDesc(userService.getUserById(userId))));
    }

    @PostMapping("/assign-role")
    public ResponseEntity<?> assignNewRole(@Valid @RequestBody AssignRoleForm assignRoleForm) {
        String email = assignRoleForm.getEmail();
        String newRoleAsString = assignRoleForm.getRole();
        Role newRole = userService.findByRole(Roles.valueOf(newRoleAsString));

        User user = userService.getUserByEmail(email);
        user.setRole(newRole);
        userService.save(user);

        return ResponseEntity.ok().body(email + "'s role has been changed to " + newRole.getRole().toString());
    }

    @GetMapping("/collection-event")
    public ResponseEntity<List<CollectionEventDto>> getCollectionEvents() {
        return ResponseEntity.ok(collectionEventService.getCollectionEventListDtos());
    }

    @PostMapping("/collection-event/new")
    public ResponseEntity<?> addCollectionEvent(@RequestBody CollectionEventDto collectionEventDto) {
        CollectionEvent collectionEvent = new CollectionEvent();
        collectionEvent.setEventDetail(collectionEventDto.getEventDetail());
        collectionEvent.setEventLatitude(collectionEventDto.getLat());
        collectionEvent.setEventLongitude(collectionEventDto.getLng());
        collectionEvent.setMaterialType(collectionEventDto.getMaterialType());
        collectionEvent.setEventDate(collectionEventDto.getCollectionEventDate());

        collectionEventService.addCollectionEvent(collectionEvent);

        // try {
        //     firebaseMessagingService.sendNotification(collectionEventDto.getLat(), collectionEventDto.getLng());
        // } catch (FirebaseMessagingException e) {
        //     e.printStackTrace();
        //     return ResponseEntity.badRequest().body("Something bad happened!");
        // }

        return ResponseEntity.ok("Collection event added successfully");
    }

    @DeleteMapping("/collection-event/delete/{collectionEventId}")
    public ResponseEntity<?> deleteCollectionEvent(@PathVariable Integer collectionEventId) {
        collectionEventService.deleteCollectionEvent(collectionEventService.getCollectionEventById(collectionEventId));
        return ResponseEntity.ok("Collection event deleted successfully");
    }

    @PutMapping("/collection-event/update")
    public ResponseEntity<?> updateRecyclePoint(@RequestBody CollectionEventDto collectionEventDto) {
        CollectionEvent collectionEvent = collectionEventService
                .getCollectionEventById(collectionEventDto.getCollectionEventId());
        collectionEvent.setEventDetail(collectionEventDto.getEventDetail());
        collectionEvent.setEventLatitude(collectionEventDto.getLat());
        collectionEvent.setEventLongitude(collectionEventDto.getLng());
        collectionEvent.setMaterialType(collectionEventDto.getMaterialType());
        collectionEvent.setEventDate(collectionEventDto.getCollectionEventDate());

        collectionEventService.addCollectionEvent(collectionEvent);
        return ResponseEntity.ok("Collection event updated successfully");
    }

    @PostMapping("/news")
    public ResponseEntity<?> addNew(@RequestBody NewForm newForm) {
        News newNews = new News(newForm.getHeading(), newForm.getContent(), newForm.getImageUrl());
        boolean isSuccess = newsService.addNews(newNews);
        return isSuccess ? ResponseEntity.ok("Added") : ResponseEntity.badRequest().body("Something went wrong");
    }
}
