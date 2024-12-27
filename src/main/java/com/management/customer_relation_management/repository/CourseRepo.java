package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.customer_relation_management.entities.Course;


public interface CourseRepo extends JpaRepository<Course,Long>{
    
}
