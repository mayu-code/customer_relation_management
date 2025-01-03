package com.management.customer_relation_management.service.serviceInterface;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.management.customer_relation_management.dto.MonthWiseRegistration;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;

public interface RegistrationService {

    RegistrationForm createRegistration(RegistrationForm registrationForm,Manager manager);
    RegistrationForm updateRegistrationForm(RegistrationForm registrationForm);
    
    RegistrationForm getreRegistrationFormByEmail(String email);
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

    List<RegistrationForm> getDueRegistrationForms(Manager manager);

    List<String> getAllDistinctColleges();
    List<String> getAllDistinctQualification();
    List<String> getAllDistinctBranch();
    List<RegistrationForm> top5RegistrationForms(Manager manager,Pageable pageable);
    List<RegistrationForm> top5RegistrationForms(Pageable pageable);

    // for admin 
    public List<RegistrationForm> getAllFormsForAdmin();
    public List<RegistrationForm> getAllDueFroFormsForAdmin();
    // public List<MonthWiseRegistration> getMonthWiseRegistration(LocalDate localDate);
}
