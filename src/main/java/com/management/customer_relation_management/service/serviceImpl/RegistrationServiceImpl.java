package com.management.customer_relation_management.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.repository.RegistrationRepository;
import com.management.customer_relation_management.service.serviceInterface.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public RegistrationForm createRegistration(RegistrationForm registrationForm) {
        return registrationRepository.save(registrationForm);
    }

    @Override
    public RegistrationForm getRegistrationFormById(long id) {
        return registrationRepository.findById(id).orElse(null);
    }

}
