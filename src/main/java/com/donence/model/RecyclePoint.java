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
    private String id;

    @Column(name = "recycle_point_name")
    private String recylePointName;

    @Column(name = "geolocation")
    private Point geolocation;
}
