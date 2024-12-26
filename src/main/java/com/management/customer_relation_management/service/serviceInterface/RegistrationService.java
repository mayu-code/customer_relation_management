package com.management.customer_relation_management.service.serviceInterface;

import com.management.customer_relation_management.entities.RegistrationForm;

public interface RegistrationService {

    RegistrationForm createRegistration(RegistrationForm registrationForm);

    RegistrationForm getRegistrationFormById(long id);
}
