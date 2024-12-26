package com.management.customer_relation_management.service.serviceInterface;

import com.management.customer_relation_management.entities.Admin;

public interface AdminService {
    
    Admin getAdminByEmail(String email);
}
