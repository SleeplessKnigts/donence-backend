package com.donence.service;

import java.util.List;

import com.donence.dto.request.Note;
import com.donence.model.User;
import com.donence.service.abstracts.UserService;
import com.google.firebase.messaging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    UserService userService;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(Note note, String token) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(note.getSubject()).setBody(note.getContent())
                .setImage(note.getImage()).build();

        Message message = Message.builder().setToken(token).setNotification(notification).putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }

    public void sendNotification(Double lat, Double lon) throws FirebaseMessagingException {
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            Double dist = distance(lat, user.getLatitude(), lon, user.getLongitude());
            if(dist < 2){
                Note note = new Note();
                note.setSubject("Yeni Etkinlik");
                note.setContent("Civarinizda yeni bir etkinlik olusturuldu!");
                sendNotification(note, user.getDeviceToken());
            }
            
        }
    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}