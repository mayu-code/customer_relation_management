package com.management.customer_relation_management.JwtSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.service.serviceImpl.AdminServiceImpl;

@Service
public class AdminCustomDetail implements UserDetailsService {

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = this.adminService.getAdminByEmail(username);

        if(admin==null){
            throw new UsernameNotFoundException("invalid credenitials");
        }
        return User.builder().username(admin.getEmail()).password(admin.getPassword()).roles(admin.getRole().toString()).authorities(admin.getAuthorities()).build();
    }
    
}
