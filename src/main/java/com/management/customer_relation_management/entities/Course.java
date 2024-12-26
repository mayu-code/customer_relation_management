package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String courseName;

    private String courseDescription;

    private String courseDuration;

    private long price;

    @JsonIgnoreProperties("registeredCourses")
    @ManyToMany
    private List<RegistrationForm> registrationForms = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    private EnquiryForm enquiryForm;

}
