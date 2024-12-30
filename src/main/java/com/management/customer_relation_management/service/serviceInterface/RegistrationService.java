package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;

public interface RegistrationService {

    RegistrationForm createRegistration(RegistrationForm registrationForm,Manager manager);
    RegistrationForm updateRegistrationForm(RegistrationForm registrationForm);
    
    RegistrationForm getRegistrationFormById(long id);
    List<RegistrationForm> getRegistrationFormByManager(Manager manager);
    List<RegistrationForm> allRegistrationForms();

    void deleteRegistrationForm(long id);

    List<RegistrationForm> searchRegistrationFormByName(String name,Manager manager);
    List<RegistrationForm> searchRegistrationFormById(String id,Manager manager);
    List<RegistrationForm> searchRegistrationFormsByBranch(String branch,Manager manager);
    List<RegistrationForm> searchRegistrationFormsByQualification(String branch,Manager manager);
    List<RegistrationForm> searchRegistrationFormsByCollege(String branch,Manager manager);
    List<RegistrationForm> searchRegistrationFormsByCourse(String courseName ,Manager manager);

    List<String> getAllDistinctColleges();
    List<String> getAllDistinctQualification();
    List<String> getAllDistinctBranch();

    
}
