package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.repository.EnquiryFormRepo;
import com.management.customer_relation_management.service.serviceInterface.EnquiryFormService;

@Service
public class EnquiryFormServiceImpl implements EnquiryFormService {

    @Autowired
    EnquiryFormRepo enquiryFormRepo;

    @Override
    public EnquiryForm addEnquiryForm(EnquiryForm enquiryForm,Manager manager) {
        enquiryForm.setManager(manager);
        return this.enquiryFormRepo.save(enquiryForm);
    }

    @Override
    public List<EnquiryForm> enquiryFormByManager(Manager manager) {
        return this.enquiryFormRepo.findByManager(manager);
    }

    @Override
    public List<EnquiryForm> allEnquiryForms() {
        return this.enquiryFormRepo.findAll();
    }

    @Override
    public EnquiryForm updateEnquiryForm(EnquiryForm enquiryForm) {
         return this.enquiryFormRepo.save(enquiryForm);
    }

    @Override
    public void deleteEnquiry(long id) {
        this.enquiryFormRepo.deleteById(id);
        return ;
    }

    @Override
    public EnquiryForm getEnquiryFormById(long id) {
        return this.enquiryFormRepo.findById(id).get();
    }

    @Override
    public List<EnquiryForm> getEnquiryFormsByCourseName(String courseName) {
         return this.enquiryFormRepo.findByCourseName(courseName);
    }
    
}
