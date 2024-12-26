package com.management.customer_relation_management.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;
    private String name;
    private String cantact;
    private String password;
}
