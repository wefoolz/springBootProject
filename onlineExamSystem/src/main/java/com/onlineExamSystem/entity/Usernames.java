package com.onlineExamSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usernames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNamesId;

    private String userNames;
    
    private String roles;
}
