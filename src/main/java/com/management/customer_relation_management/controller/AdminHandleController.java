package com.management.customer_relation_management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.entities.GetCourse;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.service.serviceImpl.GetCoursesServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class AdminHandleController {

    @Autowired
    GetCoursesServiceImpl getCoursesServiceImpl;

    @PostMapping("/addGetCourse")
    public ResponseEntity<DataResponse> addGetCourse(@RequestBody GetCourse course) {
        DataResponse response = new DataResponse();
        try {
            response.setData(this.getCoursesServiceImpl.addGetCourse(course));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("New Course Add successfully !");
            return ResponseEntity.of(Optional.of(response));
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/deleteGetCourse/{id}")
    public ResponseEntity<SuccessResponse> deleteGetCourse(@PathVariable("id") long id) {
        SuccessResponse response = new SuccessResponse();
        try {
            this.getCoursesServiceImpl.deleteCourse(id);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Course delete successfully !");
            return ResponseEntity.of(Optional.of(response));
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
