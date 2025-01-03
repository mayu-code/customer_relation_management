package com.management.customer_relation_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.EnquiryForm;

import jakarta.transaction.Transactional;


public interface CourseRepo extends JpaRepository<Course,Long>{
    
    @Query("SELECT DISTINCT c.courseName FROM Course c")
    List<String> findAllDistinctCourses();

     @Modifying
    @Transactional
    @Query("DELETE  FROM Course c WHERE c.enquiryForm.id = :enquiryFormId")
    void deleteByEnquiryFormId(@Param("enquiryFormId") Long enquiryFormId);

    

}
