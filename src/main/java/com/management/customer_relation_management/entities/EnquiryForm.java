package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class EnquiryForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", initialValue = 100000, allocationSize = 1)
    private long id;

    private String enquiryDate;

    private String name;

    private String contact;

    private String email;

    private String college;

    private String branch;

    private String knowAbout;

    private String qualifications;

    private String registeredCources;

    @JsonIgnoreProperties("enquiryForm")
    @OneToMany(mappedBy = "enquiryForm", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EnquiryDetail> enquiryDetails = new ArrayList<>();

    @JsonIgnoreProperties("equiries")
    @ManyToOne
    private Manager manager;

}
