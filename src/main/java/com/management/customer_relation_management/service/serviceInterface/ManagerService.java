package com.management.customer_relation_management.service.serviceInterface;

import java.util.UUID;

import com.management.customer_relation_management.entities.Manager;

public interface ManagerService {

    Manager getManagerByEmail(String email);

    Manager saveManager(Manager manager);

    Manager getManagerById(UUID id);
}
