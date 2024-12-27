package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String courseName;

    private String courseDuration;

    private long price;

    @JsonIgnore
    @ManyToOne
    private RegistrationForm registrationForm ;

    @JsonIgnore
    @ManyToOne
    private EnquiryForm enquiryForm;

}
