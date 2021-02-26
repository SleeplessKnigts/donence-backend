package com.donence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "requests")
@NoArgsConstructor
@Getter
@Setter
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "created_at")
    private LocalDateTime creationDate;

    @Column(name = "active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issuer", nullable = false)
    @JsonIgnoreProperties(value={"id", "username", "fName", "authProvider", "imageUrl", "role", "requests"})
    private User issuer;

    public Request(String requestType, User issuer){
        this.isActive = true;
        this.creationDate = LocalDateTime.now();
        this.requestType = requestType;
        this.issuer = issuer;
    }

}
