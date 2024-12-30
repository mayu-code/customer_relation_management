package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.RegistrationForm;

public interface CourseService {
    
    Course addEnquiryCourse(Course course,EnquiryForm enquiryForm);
    Course addRegisterCourse(Course course,RegistrationForm registrationForm);
    List<String> getDistinctCourse();
}
