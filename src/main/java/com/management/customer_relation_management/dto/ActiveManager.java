package com.management.customer_relation_management.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ActiveManager {
    private UUID id;
    private boolean active ;
}
