package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;

public interface RegistrationService {

    RegistrationForm createRegistration(RegistrationForm registrationForm,Manager manager);

    RegistrationForm getRegistrationFormById(long id);

    RegistrationForm updateRegistrationForm(RegistrationForm registrationForm);

    List<RegistrationForm> getRegistrationFormByManager(Manager manager);

    List<RegistrationForm> allRegistrationForms();

    void deleteRegistrationForm(long id);

    List<RegistrationForm> searchRegistrationFormByName(String name);

    List<RegistrationForm> searchRegistrationFormById(String id);

    
}
