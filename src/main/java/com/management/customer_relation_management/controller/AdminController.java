package com.management.customer_relation_management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.dto.ApproveManager;
import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.service.serviceImpl.AdminServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.EnquiryFormServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class AdminController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @Autowired
    private RegistrationServiceImpl registrationServiceImpl;

    @Autowired
    private EnquiryFormServiceImpl enquiryFormServiceImpl;

    @GetMapping("/getAdmin")
    public ResponseEntity<DataResponse> getAdmin(@RequestHeader("Authorization") String jwt) {
        Admin admin = this.adminServiceImpl.getAdminByJwt(jwt);

        if (admin == null) {
            DataResponse res = new DataResponse();
            res.setMessage("invalid token");
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }

        DataResponse res = new DataResponse();
        res.setMessage("Admin Fetch Successfully");
        res.setStatus(HttpStatus.OK);
        res.setData(admin);
        res.setStatusCode(200);

        return ResponseEntity.of(Optional.of(res));

    }

    @GetMapping("/getAllManagers")
    public ResponseEntity<DataResponse> getAllMangers(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.managerServiceImpl.getAllManagers());
            response.setMessage("managers get successfully !");
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("something went wrong !");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/updateManager")
    public ResponseEntity<SuccessResponse> updateManager(@RequestBody Manager manager){
        SuccessResponse response = new SuccessResponse();
        try{
           this.managerServiceImpl.updateManager(manager);
           response.setStatus(HttpStatus.OK);
           response.setStatusCode(200);
           response.setMessage("update manager successfully !");
           return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/approveManager")
    public ResponseEntity<SuccessResponse> approveManager(@RequestBody ApproveManager approve){
        SuccessResponse response = new SuccessResponse();
        Manager manager = this.managerServiceImpl.getManagerById(approve.getId());
        if(approve.isApproved()){
            manager.setApproved(approve.isApproved());
            response.setMessage("manager approved successfully !");
        }else{
            manager.setApproved(approve.isApproved());
            response.setMessage("manager disable successfully !");
        }
        try{
           this.managerServiceImpl.updateManager(manager);
           response.setStatus(HttpStatus.OK);
           response.setStatusCode(200);
           return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/registrations")
    public ResponseEntity<DataResponse> getAllRegistration(){
        DataResponse response = new DataResponse();
        try{
            response.setData(registrationServiceImpl.getAllFormsForAdmin());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("registration forms get succusfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDueForm")
    public ResponseEntity<DataResponse> getDueRegistrations(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.registrationServiceImpl.getAllDueFroFormsForAdmin());
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Get due form successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/enquiries")
    public ResponseEntity<DataResponse> getAllEnquiry(){
        
        DataResponse response = new DataResponse();
        try{
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("enquiryform get succusfully !");
            response.setData(this.enquiryFormServiceImpl.getAllEnquiryFormsForAdmin());
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
