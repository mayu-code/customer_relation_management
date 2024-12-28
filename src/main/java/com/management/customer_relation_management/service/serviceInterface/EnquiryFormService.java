package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;

public interface EnquiryFormService {

    EnquiryForm addEnquiryForm(EnquiryForm enquiryForm,Manager manager);
    List<EnquiryForm> enquiryFormByManager(Manager manager);
    List<EnquiryForm> allEnquiryForms();
    EnquiryForm updateEnquiryForm(EnquiryForm enquiryForm);
    void deleteEnquiry(long id);
    EnquiryForm getEnquiryFormById(long id);
    List<EnquiryForm> getEnquiryFormsByCourseName(String courseName);
} 
