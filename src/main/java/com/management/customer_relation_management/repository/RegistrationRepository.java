package com.management.customer_relation_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;


import java.util.List;

// @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
// public List<User> searchUser(@Param("query") String query);

public interface RegistrationRepository extends JpaRepository<RegistrationForm, Long> {

    List<RegistrationForm> findByManager(Manager manager);
    List<RegistrationForm> findByCollege(String college);
    List<RegistrationForm> findByBranch(String branch);

    @Query("SELECT r FROM RegistrationForm r WHERE r.name LIKE %:query% or r.email LIKE %:query%")
    List<RegistrationForm> searchRegistrationFormsByName(@Param("query") String query);

    @Query("SELECT r FROM RegistrationForm r WHERE CAST(r.id AS string) LIKE %:query%")
    List<RegistrationForm> searchRegistrationFormsById(@Param("query") String query);



    @Query("SELECT DISTINCT r.college FROM RegistrationForm r")
    List<String> findAllDistinctColleges();

    @Query("SELECT DISTINCT r.college FROM RegistrationForm r")
    List<String> findAllDistinctBranch();

    @Query("SELECT DISTINCT r.qualification FROM RegistrationForm r")
    List<String> findAllDistinctQualifications();

}
