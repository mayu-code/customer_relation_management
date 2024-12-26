package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.customer_relation_management.entities.RegistrationForm;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Long> {

}
