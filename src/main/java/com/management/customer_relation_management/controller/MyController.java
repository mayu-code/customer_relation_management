package com.management.customer_relation_management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.service.serviceImpl.GetCoursesServiceImpl;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class MyController {

    @Autowired
    GetCoursesServiceImpl coursesServiceImpl;

    @GetMapping("/getAllCourse")
    public ResponseEntity<DataResponse> getAllCourses() {
        DataResponse response = new DataResponse();
        try {
            response.setData(this.coursesServiceImpl.getAllCourses());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Get all courses successfully !");
            return ResponseEntity.of(Optional.of(response));
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
