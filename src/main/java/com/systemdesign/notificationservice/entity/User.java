package com.systemdesign.notificationservice.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String emailId;

    @Column
    private String password;

    @Column
    private String userId;
}
