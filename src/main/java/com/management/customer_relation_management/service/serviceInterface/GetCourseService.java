package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.GetCourse;

public interface GetCourseService {
    

    GetCourse addGetCourse(GetCourse course);
    List<GetCourse> getAllCourses();
    void deleteCourse(long id);
}
