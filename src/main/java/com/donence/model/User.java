package com.donence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
// Lombok is used to prevent verbose class structure. @Data provides essential
// methods like getter, setter, hashCode etc.
@Data
@NoArgsConstructor
@Table(name = "users", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
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

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
