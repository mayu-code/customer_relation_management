package com.management.customer_relation_management.service.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.repository.ManagerRepo;
import com.management.customer_relation_management.service.serviceInterface.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepo managerRepo;

    @Override
    public Manager getManagerByEmail(String email) {
        return this.managerRepo.findByEmail(email);
    }

    @Override
    public Manager saveManager(Manager manager) {
        return this.managerRepo.save(manager);
    }

    @Override
    public Manager getManagerById(UUID id) {
        return this.managerRepo.findById(id).orElse(null);
    }

}
