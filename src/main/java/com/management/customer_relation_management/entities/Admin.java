package com.management.customer_relation_management.entities;

import java.util.UUID;

import com.management.customer_relation_management.role.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String name;
    private String cantact;
    private String password;
    private Roles role=Roles.ADMIN;
    private String registationDate;
    private String loginDate;

    
}
