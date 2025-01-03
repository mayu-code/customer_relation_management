package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.repository.CourseRepo;
import com.management.customer_relation_management.service.serviceInterface.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepo courseRepo;

    @Override
    public Course addEnquiryCourse(Course course, EnquiryForm enquiryForm) {
        course.setEnquiryForm(enquiryForm);
        return this.courseRepo.save(course);
    }

    @Override
    public Course addRegisterCourse(Course course, RegistrationForm registrationForm) {
         course.setRegistrationForm(registrationForm);
         return this.courseRepo.save(course);
    }

    @Override
    public List<String> getDistinctCourse() {
        return this.courseRepo.findAllDistinctCourses();
    }

    public void deleteCourses(long id){
        this.courseRepo.deleteByEnquiryFormId(id);
        return;
    }

    
}
