package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


}
