package com.management.customer_relation_management.service.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.JwtSecurity.JwtProvider;
import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.repository.AdminRepo;
import com.management.customer_relation_management.service.serviceInterface.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Override
    public Admin getAdminByEmail(String email) {
        return this.adminRepo.findByEmail(email);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return this.adminRepo.save(admin);
    }

    @Override
    public Admin getAdminById(UUID id) {
        return this.adminRepo.findById(id).orElse(null);
    }

    @Override
    public Admin getAdminByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        Admin admin = this.adminRepo.findByEmail(email);
        return admin;
    }

}
