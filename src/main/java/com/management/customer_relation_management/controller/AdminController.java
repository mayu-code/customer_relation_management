package com.management.customer_relation_management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.service.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class AdminController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

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
}
