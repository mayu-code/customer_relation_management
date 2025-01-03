package com.management.customer_relation_management.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.entities.Receipt;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.helper.MailFomater;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.service.serviceImpl.ReceiptServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class PayController {


    @Autowired
    RegistrationServiceImpl registrationServiceImpl;

    @Autowired
    ReceiptServiceImpl receiptServiceImpl;

    @Autowired
    MailFomater mailFomater;
    
    @PostMapping("/payAmount")
    public ResponseEntity<DataResponse> payAmount(@RequestBody RegistrationForm registrationForm){
        RegistrationForm form = this.registrationServiceImpl.getreRegistrationFormByEmail(registrationForm.getEmail());
        Receipt receipt = new Receipt();
        DataResponse response = new DataResponse();
        receipt.setDate(DateTimeFormatter.format(LocalDateTime.now()));
        receipt.setPaymentType(registrationForm.getPaymentType());
        receipt.setRecievedAmount(registrationForm.getAmountPaid());
        receipt.setRegistrationForm(form);
        receipt.setSender(registrationForm.getName());
        receipt.setTowards(form.getManager().getName());
        
        form.setAmountPaid(form.getAmountPaid()+registrationForm.getAmountPaid());
        if(form.getInstallmentsMonths()<=1){
            double amount = form.getTotalFees()-form.getAmountPaid();
            form.setInstallments(amount);
        }
        else{
            double amount = (form.getTotalFees()-form.getAmountPaid())/(form.getInstallmentsMonths()-1);
            form.setInstallments(amount);
        }

        form.setInstallmentsMonths(form.getInstallmentsMonths()-1);
        if(form.getInstallments()>0 && form.getInstallmentsMonths()==0){
            form.setInstallmentsMonths(1);
        }
        if(form.getTotalFees()<=form.getAmountPaid()){
            form.setDeuDate("completed");
            form.setInstallmentsMonths(0);
        }
        else{
        String date = DateTimeFormatter.format(DateTimeFormatter.deformat(form.getDeuDate()).plusMonths(1));
        form.setDeuDate(date);
        }
        this.registrationServiceImpl.updateRegistrationForm(form);

        try{
            receipt = this.receiptServiceImpl.addReceipt(receipt);
            long id = receipt.getId();
            CompletableFuture.runAsync(()->mailFomater.paymentMail(id, registrationForm.getEmail()));
            response.setData(this.receiptServiceImpl.addReceipt(receipt));
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Payment Successfully !");
            return ResponseEntity.of(Optional.of(response));
        }
        catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setMessage("Something Went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
