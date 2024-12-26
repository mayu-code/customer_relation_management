package com.management.customer_relation_management.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SuccessResponse {

    private HttpStatus status;
    private String message;
    private int statusCode;
}
