package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.repository.RegistrationRepository;
import com.management.customer_relation_management.service.serviceInterface.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

        @Autowired
        private RegistrationRepository registrationRepository;
    
        @Override
        public RegistrationForm createRegistration(RegistrationForm registrationForm ,Manager manager) {
            registrationForm.setManager(manager);
            return registrationRepository.save(registrationForm);
        }
    
        @Override
        public RegistrationForm getRegistrationFormById(long id) {
            return registrationRepository.findById(id).orElse(null);
        }
    
        @Override
        public RegistrationForm updateRegistrationForm(RegistrationForm registrationForm) {
            return this.registrationRepository.save(registrationForm);
        }
    
        @Override
        public List<RegistrationForm> getRegistrationFormByManager(Manager manager) {
            return this.registrationRepository.findByManager(manager);
        }
    
        @Override
        public List<RegistrationForm> allRegistrationForms() {
            return this.registrationRepository.findAll();
        }
    
        @Override
        public void deleteRegistrationForm(long id) {
             this.registrationRepository.deleteById(id);
             return ;
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormByName(String name,Manager manager) {
            return this.registrationRepository.searchRegistrationFormsByName(name,manager);
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormById(String id,Manager manager) {
            return this.registrationRepository.searchRegistrationFormsById(id,manager);
        }
    
        @Override
        public List<String> getAllDistinctColleges() {
            return this.registrationRepository.findAllDistinctColleges();
        }
    
        @Override
        public List<String> getAllDistinctQualification() {
            return this.registrationRepository.findAllDistinctQualifications();
        }
    
        @Override
        public List<String> getAllDistinctBranch() {
            return this.registrationRepository.findAllDistinctBranch();
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormsByBranch(String branch, Manager manager) {
            return this.registrationRepository.findByBranchAndManager(branch,manager);
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormsByQualification(String branch, Manager manager) {
            return this.registrationRepository.findByQualificationAndManager(branch, manager);
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormsByCollege(String branch, Manager manager) {
            return this.registrationRepository.findByCollegeAndManager(branch, manager);
        }
    
        @Override
        public List<RegistrationForm> searchRegistrationFormsByCourse(String courseName, Manager manager) {
            return this.registrationRepository.findByCourseName(courseName, manager);
        }
    
        @Override
        public RegistrationForm getreRegistrationFormByEmail(String email) {
            return this.registrationRepository.findByEmail(email);
        }
    
        @Override
        public List<RegistrationForm> getDueRegistrationForms(Manager manager) {
            return this.registrationRepository.findDueEntries(manager);
        }
    
        public List<RegistrationForm> getAllFormsForAdmin(){
            return this.registrationRepository.findAll();
        }
    
        public List<RegistrationForm> getAllDueFroFormsForAdmin(){
            return this.registrationRepository.findAllDueEntries();
        }

        @Override
        public List<RegistrationForm> top5RegistrationForms(Manager manager, Pageable pageable) {
            return  this.registrationRepository.findFirst5Registrations(manager, pageable);
        }

        @Override
        public List<RegistrationForm> top5RegistrationForms(Pageable pageable) {
            return this.registrationRepository.findFirst5Registrations(pageable);
        }


        // @Override
        // public List<MonthWiseRegistration> getMonthWiseRegistration(LocalDate localDate) {
        //     return this.registrationRepository.findRegistrationCountByMonth(localDate);
        // }
    

    

}
