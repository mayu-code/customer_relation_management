package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;
import java.util.List;


public interface RegistrationRepository extends JpaRepository<RegistrationForm, Long> {

    List<RegistrationForm> findByManager(Manager manager);
}
