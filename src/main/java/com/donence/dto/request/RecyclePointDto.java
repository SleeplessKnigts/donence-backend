package com.donence.dto.request;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.validation.constraints.NotBlank;

@Data
public class RecyclePointDto {

    @NotBlank
    private String recyclePointDetail;

    private Double lat;

    private Double lng;

    public RecyclePointDto(@NotBlank String recyclePointDetail, Double lat, Double lng) {
        this.recyclePointDetail = recyclePointDetail;
        this.lat = lat;
        this.lng = lng;
    }
}
