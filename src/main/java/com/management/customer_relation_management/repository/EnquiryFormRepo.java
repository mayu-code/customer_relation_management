package com.management.customer_relation_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;

public interface EnquiryFormRepo extends JpaRepository<EnquiryForm,Long> {
    
    List<EnquiryForm> findByManager(Manager manager);

    @Query("SELECT e FROM EnquiryForm e JOIN e.courses c WHERE c.courseName = :courseName")
    List<EnquiryForm> findByCourseName(String courseName);
}
