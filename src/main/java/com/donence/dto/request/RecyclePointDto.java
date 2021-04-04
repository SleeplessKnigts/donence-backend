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

    private String recyclyPointPlaceType;

    public RecyclePointDto(@NotBlank String recyclePointDetail, Double lat, Double lng, String recyclePointPlaceType) {
        this.recyclePointDetail = recyclePointDetail;
        this.lat = lat;
        this.lng = lng;
        this.recyclyPointPlaceType = recyclePointPlaceType;
    }
}
