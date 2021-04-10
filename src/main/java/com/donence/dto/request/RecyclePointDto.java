package com.donence.dto.request;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.validation.constraints.NotBlank;

@Data
public class RecyclePointDto {

    private Integer recyclePointId;

    @NotBlank
    private String recyclePointDetail;

    private Double lat;

    private Double lng;

    private String recyclePointPlaceType;

    public RecyclePointDto(@NotBlank Integer id, @NotBlank String recyclePointDetail, Double lat, Double lng, String recyclePointPlaceType) {
        this.recyclePointId = id;
        this.recyclePointDetail = recyclePointDetail;
        this.lat = lat;
        this.lng = lng;
        this.recyclePointPlaceType = recyclePointPlaceType;
    }
}
