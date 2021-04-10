package com.donence.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CollectionEventDto {
    private Integer collectionEventId;
    private String materialType;
    private String eventDetail;
    private Double lat;
    private Double lng;
    private Date collectionEventDate;
}
