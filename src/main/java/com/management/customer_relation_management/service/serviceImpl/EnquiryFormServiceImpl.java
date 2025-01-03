package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public List<EnquiryForm> getEnquiryFormsByCourseName(String courseName,Manager manager) {
         return this.enquiryFormRepo.findByCourseName(courseName,manager);
    }


    @Override
    public List<EnquiryForm> searchBEnquiryFormById(String id, Manager manager) {
        return this.enquiryFormRepo.searchEnquiryFormsById(id, manager);
    }

    @Override
    public List<EnquiryForm> searchBEnquiryFormByName(String name, Manager manager) {
        return this.enquiryFormRepo.searchEnquiryFormByNameandEmail(name, manager);
    }

    @Override
    public List<EnquiryForm> searchBEnquiryFormByBranch(String branch, Manager manager) {
        return this.enquiryFormRepo.findByBranchAndManager(branch, manager);
    }

    @Override
    public List<EnquiryForm> searchBEnquiryFormByCollege(String college, Manager manager) {
        return this.enquiryFormRepo.findByCollegeAndManager(college, manager);
    }

    @Override
    public List<EnquiryForm> searchBEnquiryFormByQualification(String quali, Manager manager) {
        return this.enquiryFormRepo.findByQualificationAndManager(quali, manager);
    }

    @Override
    public List<String> getAllDistinctColleges() {
        return this.enquiryFormRepo.findAllDistinctColleges();
    }

    @Override
    public List<String> getAllDistinctQualification() {
        return this.enquiryFormRepo.findAllDistinctQualification();
    }

    @Override
    public List<String> getAllDistinctBranch() {
        return this.enquiryFormRepo.findAllDistinctBranch();
    }

    @Override
    public EnquiryForm getEnquiryFormByEmail(String email) {
        return this.enquiryFormRepo.findByEmail(email);
    }

    @Override
    public List<EnquiryForm> getAllEnquiryFormsForAdmin() {
        return this.enquiryFormRepo.findAll();
    }

    @Override
    public List<EnquiryForm> top5EnquiryForms(Manager manager, Pageable pageable) {
        return this.enquiryFormRepo.findTop5EnquiryForms(manager, pageable);
    }

    @Override
    public List<EnquiryForm> top5EnquiryForms(Pageable pageable) {
        return this.enquiryFormRepo.findTop5EnquiryForms(pageable);
    }
}
