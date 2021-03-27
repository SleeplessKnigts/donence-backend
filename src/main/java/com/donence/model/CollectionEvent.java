package com.donence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "public", name = "collection_events")
@Data
public class CollectionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_events_id")
    private Integer collectionEventId;

    @Column(name = "material_type")
    private String materialType;

    @Column(name = "event_detail")
    private String eventDetail;

    @Column(name = "event_latitude")
    private Double eventLatitude;

    @Column(name = "event_longitude")
    private Double eventLongitude;

    @Column(name = "event_date")
    private Date eventDate;

}
