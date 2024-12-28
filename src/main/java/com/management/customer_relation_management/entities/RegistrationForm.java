package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.management.customer_relation_management.paymentType.PaymentType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class RegistrationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", initialValue = 2615500, allocationSize = 1)
    private long id;

    private String registrationDate;

    private String name;

    private String contact;

    private String email;

    private String college;

    private String branch;

    private String source;

    private String qualification;

    private long totalFees;

    private long amountPaid;

    private int installmentsMonths;

    private double installments;

    private String deuDate;

    private PaymentType paymentType = PaymentType.NOT_SELECTED;

    @JsonIgnoreProperties("registrations")
    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "registrationForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> registeredCourses= new ArrayList<>();

    @OneToMany(mappedBy = "registrationForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Receipt> receipts = new ArrayList<>();
}
