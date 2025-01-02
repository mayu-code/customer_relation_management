package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Long> {

    RegistrationForm findByEmail(String email);

    @Query("SELECT r FROM RegistrationForm r WHERE r.manager = :manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> findByManager(@Param("manager")Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE r.college =:college AND r.manager =:manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> findByCollegeAndManager(String college,Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE r.branch =:branch AND r.manager =:manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> findByBranchAndManager(String branch,Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE r.qualification =:qualification AND r.manager =:manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> findByQualificationAndManager(String qualification,Manager manager);


    @Query("SELECT r FROM RegistrationForm r JOIN r.registeredCourses c WHERE c.courseName = :courseName AND r.manager =:manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> findByCourseName(@Param("courseName")String courseName,@Param("manager")Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE (r.name LIKE %:query% or r.email LIKE %:query% ) AND r.manager = :manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> searchRegistrationFormsByName(@Param("query") String query,@Param("manager") Manager Manager);

    @Query("SELECT r FROM RegistrationForm r WHERE CAST(r.id AS string) LIKE %:query% AND r.manager = :manager ORDER BY r.registrationDate DESC")
    List<RegistrationForm> searchRegistrationFormsById(@Param("query") String query,@Param("manager") Manager manager);

    @Query("SELECT r FROM RegistrationForm r WHERE CAST(r.deuDate AS date) <= CURRENT_DATE AND r.manager = :manager ORDER BY r.deuDate ASC")
    List<RegistrationForm> findDueEntries(@Param("manager") Manager manager);


    @Query("SELECT DISTINCT r.college FROM RegistrationForm r")
    List<String> findAllDistinctColleges();

    @Query("SELECT DISTINCT r.branch FROM RegistrationForm r")
    List<String> findAllDistinctBranch();

    @Query("SELECT DISTINCT r.qualification FROM RegistrationForm r")
    List<String> findAllDistinctQualifications();


    // RegistrationFrom credentials 
    @Query("SELECT r FROM RegistrationForm r WHERE CAST(r.deuDate AS date) <= CURRENT_DATE ORDER BY r.deuDate ASC")
    List<RegistrationForm> findAllDueEntries();

}
