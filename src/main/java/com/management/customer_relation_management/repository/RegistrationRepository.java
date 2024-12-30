package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Long> {

    List<RegistrationForm> findByManager(Manager manager);
    List<RegistrationForm> findByCollegeAndManager(String college,Manager manager);
    List<RegistrationForm> findByBranchAndManager(String branch,Manager manager);
    List<RegistrationForm> findByQualificationAndManager(String qualification,Manager manager);

    @Query("SELECT r FROM RegistrationForm r JOIN r.registeredCourses c WHERE c.courseName = :courseName AND r.manager =:manager")
    List<RegistrationForm> findByCourseName(String courseName,Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE (r.name LIKE %:query% or r.email LIKE %:query% ) AND r.manager = :manager")
    List<RegistrationForm> searchRegistrationFormsByName(@Param("query") String query,@Param("manager") Manager Manager);

    @Query("SELECT r FROM RegistrationForm r WHERE CAST(r.id AS string) LIKE %:query% AND r.manager = :manager")
    List<RegistrationForm> searchRegistrationFormsById(@Param("query") String query,@Param("manager") Manager manager);

    @Query("SELECT DISTINCT r.college FROM RegistrationForm r")
    List<String> findAllDistinctColleges();

    @Query("SELECT DISTINCT r.branch FROM RegistrationForm r")
    List<String> findAllDistinctBranch();

    @Query("SELECT DISTINCT r.qualification FROM RegistrationForm r")
    List<String> findAllDistinctQualifications();

}
