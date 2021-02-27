package com.donence.dto.request;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.validation.constraints.NotBlank;

@Data
public class RecyclePointDto {

    @NotBlank
    private String recyclePointDetail;

    private Double latitude;

    private Double longitude;
}
