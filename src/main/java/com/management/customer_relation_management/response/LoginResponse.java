package com.management.customer_relation_management.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class LoginResponse {

    private HttpStatus status;
    private int statusCode;
    private String message;
    private String token;
}
