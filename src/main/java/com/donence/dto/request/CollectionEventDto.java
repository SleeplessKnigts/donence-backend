package com.donence.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionEventDto {
    private String materialType;
    private String eventDetail;
    private Double lat;
    private Double lng;
}
