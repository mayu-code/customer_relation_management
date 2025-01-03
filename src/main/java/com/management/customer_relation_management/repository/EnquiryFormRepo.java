package com.management.customer_relation_management.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;

public interface EnquiryFormRepo extends JpaRepository<EnquiryForm,Long> {
    
    EnquiryForm findByEmail(String email);

    @SuppressWarnings("null")
    @Query("SELECT e FROM EnquiryForm e ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findAll();

    @Query("SELECT e FROM EnquiryForm e WHERE e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findByManager(Manager manager);

    @Query("SELECT e FROM EnquiryForm e WHERE e.branch =:branch AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findByBranchAndManager(String branch,Manager manager);

    @Query("SELECT e FROM EnquiryForm e WHERE e.branch =:branch AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findByCollegeAndManager(String college,Manager manager);

    @Query("SELECT e FROM EnquiryForm e WHERE e.qualification =:qualification AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findByQualificationAndManager(String qualification,Manager manager);

    @Query("SELECT e FROM EnquiryForm e JOIN e.courses c WHERE c.courseName = :courseName AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findByCourseName(@Param("courseName")String courseName,@Param("manager")Manager manager);

    @Query("SELECT e FROM EnquiryForm e WHERE CAST(e.id AS string) LIKE %:id% AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> searchEnquiryFormsById(@Param("id")String id,@Param("manager") Manager manager);

    @Query("SELECT e FROM EnquiryForm e WHERE (e.name LIKE %:query% OR e.email LIKE %:query%) AND e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> searchEnquiryFormByNameandEmail(@Param("query") String query , @Param("manager") Manager manager);

    @Query("SELECT DISTINCT r.college FROM EnquiryForm r")
    List<String> findAllDistinctColleges();

    @Query("SELECT DISTINCT r.branch FROM EnquiryForm r")
    List<String> findAllDistinctBranch();

    @Query("SELECT DISTINCT r.qualification FROM EnquiryForm r")
    List<String> findAllDistinctQualification();

    @Query("SELECT e FROM EnquiryForm e WHERE e.manager =:manager ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findTop5EnquiryForms(@Param("manager")Manager manager,Pageable pageable);

    @Query("SELECT e FROM EnquiryForm e ORDER BY e.enquiryDate DESC")
    List<EnquiryForm> findTop5EnquiryForms(Pageable pageable);
}
