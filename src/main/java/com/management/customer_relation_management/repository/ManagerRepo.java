package com.management.customer_relation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.customer_relation_management.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager,UUID> {
    
    Manager findByEmail(String email);
}
