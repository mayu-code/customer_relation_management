package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.management.customer_relation_management.role.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Manager {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String name;
    private String cantact;
    private String password;
    private Roles role=Roles.MANAGER;
    private String registationDate;
    private String loginDate;

    private List<EnquiryForm> equiries = new ArrayList<>();

    private List<RegistrationForm> registration = new ArrayList<>();

}
