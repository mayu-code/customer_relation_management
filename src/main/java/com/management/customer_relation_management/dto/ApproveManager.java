package com.management.customer_relation_management.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ApproveManager {
    private UUID id;
    private boolean approved;
}
