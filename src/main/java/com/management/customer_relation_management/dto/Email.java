package com.management.customer_relation_management.dto;

import lombok.Data;

@Data
public class Email {
    private String to;
    private String subject;
    private String body;
}
