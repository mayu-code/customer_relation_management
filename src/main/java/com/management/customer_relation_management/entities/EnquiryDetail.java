package com.management.customer_relation_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EnquiryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long enquiryDetailId;

    private String enquiryType;

    private String enquiryDescription;

    private String studentName;

    private String enquiryDate;

    @ManyToOne
    @JsonIgnoreProperties("enquiryDetails")
    private EnquiryForm enquiryForm;
}
