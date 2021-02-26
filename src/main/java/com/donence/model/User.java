package com.donence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// Lombok is used to prevent verbose class structure. @Data provides essential
// methods like getter, setter, hashCode etc.
@Data
@NoArgsConstructor
@Table(name = "cloud_users", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 100)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "auth_provider")
    private String authProvider;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issuer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Request> requests = new HashSet<>();
    
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public User(String authProvider, String name, String email, String imageUrl, Role role){
        this.authProvider = authProvider;
        this.fName = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public void setAddress(double lat, double lon){
        setLatitude(lat);
        setLongitude(lon);
    }
}
