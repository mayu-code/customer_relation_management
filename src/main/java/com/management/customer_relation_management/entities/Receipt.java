package com.management.customer_relation_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.customer_relation_management.paymentType.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;

    private long recievedAmount;

    private PaymentType paymentType = PaymentType.NOT_SELECTED;

    private String sender;

    private String towards;

    @JsonIgnore
    @ManyToOne
    private RegistrationForm registrationForm;
}
