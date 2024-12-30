package com.management.customer_relation_management.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.helper.MailFomater;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.service.serviceImpl.CourseServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class RegistrationController {
    
    @Autowired
    RegistrationServiceImpl registrationService;

    @Autowired
    ManagerServiceImpl managerService;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    MailFomater mailFomater;

    @PostMapping("/addRegistration")
    public ResponseEntity<DataResponse> addEnquiry(@RequestHeader("Authorization") String jwt,@RequestBody RegistrationForm registrationForm){
        Manager manager = this.managerService.getManagerByJwt(jwt);
        RegistrationForm registrationForm2 = new RegistrationForm();
        DataResponse response = new DataResponse();
        registrationForm.setRegistrationDate(DateTimeFormatter.format(LocalDateTime.now()));
        List<Course> courses = registrationForm.getRegisteredCourses();
        
        try{
            registrationForm2 = this.registrationService.createRegistration(registrationForm, manager);
            for(Course course:courses){
                System.out.println(course.toString());
                courseService.addRegisterCourse(course, registrationForm2);
            }
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Regitration Successfully !");
            response.setData(registrationForm2);
            mailFomater.mail(registrationForm2.getId());
            return ResponseEntity.of(Optional.of(response));
        }
        catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setData(null);
            response.setMessage("Something Went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/updateRegistration")
    public ResponseEntity<DataResponse> updateEnquiry(@RequestBody RegistrationForm registrationForm){
        RegistrationForm registrationForm2 = new RegistrationForm();
        DataResponse response = new DataResponse();
        List<Course> courses = registrationForm.getRegisteredCourses();
        registrationForm2 = this.registrationService.getRegistrationFormById(registrationForm.getId());
        if(registrationForm.getAmountPaid()!=registrationForm2.getAmountPaid()){
            response.setMessage("you don't have a permission to change the paid amount ");
            response.setData(registrationForm2);
            response.setStatus(HttpStatus.LOCKED);
            response.setStatusCode(423);
            return ResponseEntity.of(Optional.of(response));
        }
        
        try{
            registrationForm2 = null;
            registrationForm2 = this.registrationService.updateRegistrationForm(registrationForm);
            for(Course course:courses){
                System.out.println(course.toString());
                courseService.addRegisterCourse(course, registrationForm2);
            }
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Regitration  update Successfully !");
            response.setData(registrationForm2);
            return ResponseEntity.of(Optional.of(response));
        }
        catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setData(null);
            response.setMessage("Something Went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/registrations")
    public ResponseEntity<DataResponse> getEnquiryByManager(@RequestHeader("Authorization") String jwt){
        Manager manager  = this.managerService.getManagerByJwt(jwt);
        DataResponse response = new DataResponse();
        try{
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("registration forms get succusfully !");
            response.setData(this.registrationService.getRegistrationFormByManager(manager));
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/deleteRegistration/{id}")
    public ResponseEntity<DataResponse> deleteEnquiry(@PathVariable("id") long id){
        DataResponse response = new DataResponse();
        try{
            this.registrationService.deleteRegistrationForm(id);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("delete registration successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getRegistrationById/{id}")
    public ResponseEntity<DataResponse> getRegistrationById(@RequestHeader("Authorization") String jwt,@PathVariable("id")long id){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationService.getRegistrationFormById(id));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Get registration successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchRegistrationByName/{name}")
    public ResponseEntity<DataResponse> searchRegistrationByName(@RequestHeader("Authorization") String jwt,@PathVariable("name")String name){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationService.searchRegistrationFormByName(name));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("search successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchRegistrationById/{id}")
    public ResponseEntity<DataResponse> searchRegistrationById(@RequestHeader("Authorization") String jwt,@PathVariable("id")String id){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationService.searchRegistrationFormById(id));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("search successfully !");
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
