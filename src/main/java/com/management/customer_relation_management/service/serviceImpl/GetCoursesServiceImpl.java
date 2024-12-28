package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.GetCourse;
import com.management.customer_relation_management.repository.GetCourseRepo;
import com.management.customer_relation_management.service.serviceInterface.GetCourseService;

@Service
public class GetCoursesServiceImpl implements GetCourseService {
    
    @Autowired
    GetCourseRepo courseRepo;

    @Override
    public GetCourse addGetCourse(GetCourse course) {
        return this.courseRepo.save(course);
    }

    @Override
    public List<GetCourse> getAllCourses() {
        return this.courseRepo.findAll();
    }

    @Override
    public void deleteCourse(long id) {
        this.courseRepo.deleteById(id);
        return ;
    }
    
}
