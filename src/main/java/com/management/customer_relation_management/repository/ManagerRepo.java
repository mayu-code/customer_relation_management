package com.management.customer_relation_management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.customer_relation_management.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager,UUID> {
    
    Manager findByEmail(String email);

    @Query("SELECT m FROM Manager m WHERE m.approved = TRUE ORDER BY m.registationDate DESC")
    List<Manager> getAllApprovedManager();

    @Query("SELECT m FROM Manager m WHERE m.approved = FALSE ORDER BY m.registationDate DESC")
    List<Manager> getDisApprovedManager();


}
