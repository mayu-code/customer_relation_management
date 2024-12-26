package com.management.customer_relation_management.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.repository.AdminRepo;
import com.management.customer_relation_management.service.serviceInterface.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepo adminRepo;

    @Override
    public Admin getAdminByEmail(String email) {
        return this.adminRepo.findByEmail(email);
    }
    
}
