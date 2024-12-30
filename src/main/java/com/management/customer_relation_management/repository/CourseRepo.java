package com.management.customer_relation_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.customer_relation_management.entities.Course;


public interface CourseRepo extends JpaRepository<Course,Long>{
    
    @Query("SELECT DISTINCT c.courseName FROM Course c")
    List<String> findAllDistinctCourses();

}
