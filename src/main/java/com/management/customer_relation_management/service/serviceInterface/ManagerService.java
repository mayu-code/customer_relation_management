package com.management.customer_relation_management.service.serviceInterface;

import com.management.customer_relation_management.entities.Manager;

public interface ManagerService {

    Manager getManagerByEmail(String email);

}
