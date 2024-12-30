package com.management.customer_relation_management.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.dto.Email;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.service.serviceImpl.EmailServiceImpl;

@RestController
@RequestMapping({"/manager","/admin"})
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class EmailController {
    
    @Autowired
    EmailServiceImpl emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<SuccessResponse> sendMail(@RequestBody Email email){
        SuccessResponse response =new SuccessResponse();
        try{
            CompletableFuture.runAsync(() -> emailService.sendEmail(email.getTo(), email.getSubject(),email.getBody()));
            response.setMessage("mail send successfully !");
            response.setStatus(org.springframework.http.HttpStatus.OK);
            response.setStatusCode(200);
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setMessage("something went wrong !");
            response.setStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
