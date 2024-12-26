package com.management.customer_relation_management.JwtSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;


@Service
public class ManagerCustomDetail implements UserDetailsService{

    @Autowired
    ManagerServiceImpl managerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = this.managerService.getManagerByEmail(username);
        if(manager==null){
            throw new UsernameNotFoundException("invalid credenitials");
        }
        return User.builder().username(manager.getEmail()).password(manager.getPassword())
                             .roles(manager.getRole().toString()).authorities(manager.getAuthorities()).build();
    }
    
}
