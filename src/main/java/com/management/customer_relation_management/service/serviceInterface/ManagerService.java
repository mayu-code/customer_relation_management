package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;
import java.util.UUID;

import com.management.customer_relation_management.entities.Manager;

public interface ManagerService {

    Manager getManagerByEmail(String email);

    Manager saveManager(Manager manager);

    Manager getManagerById(UUID id);

    Manager getManagerByJwt(String jwt);

    List<Manager> getAllApprovedManagers();

    List<Manager> getDisApprovedManagers();

    Manager updateManager(Manager manager);

    void deleteManager(UUID id);

    
}
