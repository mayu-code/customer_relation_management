package com.management.customer_relation_management.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.repository.ManagerRepo;
import com.management.customer_relation_management.service.serviceInterface.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    ManagerRepo managerRepo;

    @Override
    public Manager getManagerByEmail(String email) {
       return this.managerRepo.findByEmail(email);
    }
    
}
