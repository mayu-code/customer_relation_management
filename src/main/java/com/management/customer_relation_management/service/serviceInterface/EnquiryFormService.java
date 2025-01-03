package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;

public interface EnquiryFormService {

    EnquiryForm getEnquiryFormByEmail(String email);
    EnquiryForm addEnquiryForm(EnquiryForm enquiryForm,Manager manager);
    List<EnquiryForm> enquiryFormByManager(Manager manager);
    List<EnquiryForm> allEnquiryForms();
    EnquiryForm getEnquiryFormById(long id);
    EnquiryForm updateEnquiryForm(EnquiryForm enquiryForm);

    void deleteEnquiry(long id);
    
    List<EnquiryForm> getEnquiryFormsByCourseName(String courseName,Manager manager);
    List<EnquiryForm> searchBEnquiryFormById(String id,Manager manager);
    List<EnquiryForm> searchBEnquiryFormByName(String name,Manager manager);
    List<EnquiryForm> searchBEnquiryFormByBranch(String branch,Manager manager);
    List<EnquiryForm> searchBEnquiryFormByCollege(String college,Manager manager);
    List<EnquiryForm> searchBEnquiryFormByQualification(String quali,Manager manager);

    List<String> getAllDistinctColleges();
    List<String> getAllDistinctQualification();
    List<String> getAllDistinctBranch();

    List<EnquiryForm> top5EnquiryForms(Manager manager,Pageable pageable);

    // admin 
    List<EnquiryForm> getAllEnquiryFormsForAdmin();
    List<EnquiryForm> top5EnquiryForms(Pageable pageable);
} 


