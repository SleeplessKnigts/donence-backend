package com.donence.dto.response;

import com.donence.model.User;

import lombok.Data;

@Data
public class UserDetailResponse {

    private String email;

    private String imageUrl;

    private Double lat;

    private Double lng;

    private String fname;

    public UserDetailResponse(String email, String imageUrl, double lat, double lng, String fname) {
        this.email = email;
        this.fname = fname;
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lng = lng;
    }

    public UserDetailResponse(User user){
        this.email = user.getEmail();
        this.fname = user.getFName();
        this.imageUrl = user.getImageUrl();
        this.lat = user.getLatitude();
        this.lng = user.getLongitude();
    }
}
