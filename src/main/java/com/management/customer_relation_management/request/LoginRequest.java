package com.management.customer_relation_management.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}
