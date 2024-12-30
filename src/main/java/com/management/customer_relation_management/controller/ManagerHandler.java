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
import com.management.customer_relation_management.service.serviceImpl.CourseServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.EnquiryFormServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class ManagerHandler {

    @Autowired
    RegistrationServiceImpl registrationServiceImpl;

    @Autowired
    EnquiryFormServiceImpl enquiryFormServiceImpl;

    @Autowired
    CourseServiceImpl courseServiceImpl;
    
    @GetMapping("/getAllCollege")
    public ResponseEntity<DataResponse> getAllDistinctColleges(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationServiceImpl.getAllDistinctColleges());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get colleges successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getAllQualification")
    public ResponseEntity<DataResponse> getAllDistinctQualification(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationServiceImpl.getAllDistinctQualification());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get qualifications successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getAllBranch")
    public ResponseEntity<DataResponse> getAllDistinctBranch(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationServiceImpl.getAllDistinctBranch());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get branches successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDistinctCourse")
    public ResponseEntity<DataResponse> getDistinctCourses(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.courseServiceImpl.getDistinctCourse());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get courses successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDistinctEnquiryCollege")
    public ResponseEntity<DataResponse> getDistinctEnquiryCollege(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.enquiryFormServiceImpl.getAllDistinctColleges());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get colleges successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDistinctEnquiryBranch")
    public ResponseEntity<DataResponse> getDistinctEnquiryBranch(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.enquiryFormServiceImpl.getAllDistinctBranch());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get branches successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDistinctQualification")
    public ResponseEntity<DataResponse> getDistinctQualification(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.enquiryFormServiceImpl.getAllDistinctQualification());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get qualifications successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
