package com.donence.model;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recycle_points", schema = "public")
public class RecyclePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recyclePointId;

    @Column(name = "recycle_point_detail")
    private String recyclePointDetail;

    @Column(name = "recycle_point_latitude")
    private Double recyclePointLatitude;

    @Column(name = "recycle_point_longitude")
    private Double recyclePointLongitude;

    @Column(name = "recycle_point_place_type")
    private String recyclePointPlaceType;

    public void setGeolocation(Double latitude, Double longitude){
        this.recyclePointLatitude = latitude;
        this.recyclePointLongitude = longitude;
    }
}
