package com.management.customer_relation_management.controller;

import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.entities.Receipt;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.helper.MailFomater;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.service.serviceImpl.CourseServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ImageUploader;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ReceiptServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class RegistrationController {
    
    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private ManagerServiceImpl managerService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private MailFomater mailFomater;

    @Autowired
    private ReceiptServiceImpl receiptServiceImpl;

    @Autowired
    private ImageUploader imageUploader;

    @PostMapping("/addRegistration")
    public ResponseEntity<DataResponse> addEnquiry(@RequestHeader("Authorization") String jwt,
                                                    @RequestPart(value = "image", required = false) MultipartFile image,
                                                    @RequestPart("registrationForm") RegistrationForm registrationForm){
        Manager manager = this.managerService.getManagerByJwt(jwt);
        RegistrationForm registrationForm2 = new RegistrationForm();
        RegistrationForm isRegister = this.registrationService.getreRegistrationFormByEmail(registrationForm.getEmail());
        DataResponse response = new DataResponse();

        if(isRegister!=null){
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Regitration alredy exits please use another email !");
            return ResponseEntity.of(Optional.of(response));
        }

        registrationForm.setRegistrationDate(DateTimeFormatter.format(LocalDateTime.now()));
        registrationForm.setImageUrl(imageUploader.iamgeUploader(image));
        if(registrationForm.getTotalFees()==registrationForm.getAmountPaid() || registrationForm.getInstallmentsMonths()<=0){
            registrationForm.setInstallments(0);
            registrationForm.setInstallmentsMonths(0);
            registrationForm.setDeuDate("NO Due Date");
        }
        else{
            registrationForm.setDeuDate(DateTimeFormatter.format(LocalDateTime.now().plusMonths(1)));
        }
        List<Course> courses = registrationForm.getRegisteredCourses(); 
        try{
            registrationForm2 = this.registrationService.createRegistration(registrationForm, manager);
            for(Course course:courses){
                courseService.addRegisterCourse(course, registrationForm2);
            }

            Receipt receipt = new Receipt();
            receipt.setDate(registrationForm.getRegistrationDate());
            receipt.setPaymentType(registrationForm.getPaymentType());
            receipt.setRecievedAmount(registrationForm.getAmountPaid());
            receipt.setSender(registrationForm.getName());
            receipt.setTowards(manager.getName());
            receipt.setRegistrationForm(registrationForm2);

            response.setData(this.receiptServiceImpl.addReceipt(receipt));
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Regitration Successfully !");
            long id = registrationForm2.getId();
            CompletableFuture.runAsync(() -> mailFomater.registrationMail(id));
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

    @PostMapping("/updateRegistration")
    public ResponseEntity<SuccessResponse> updateRegistration(@RequestBody RegistrationForm registrationForm){
        SuccessResponse response = new SuccessResponse();
        RegistrationForm registrationForm2 = this.registrationService.getRegistrationFormById(registrationForm.getId());
        if(!registrationForm2.getEmail().equals(registrationForm.getEmail())){
            RegistrationForm isRegistrationForm = this.registrationService.getreRegistrationFormByEmail(registrationForm.getEmail());
            if(!(isRegistrationForm==null)){
            response.setMessage("email already present");
            response.setStatus(HttpStatus.LOCKED);
            response.setStatusCode(423);
            return ResponseEntity.of(Optional.of(response));
        }
        }    
        try{
            registrationForm2.setBranch(registrationForm.getBranch());
            registrationForm2.setCollege(registrationForm.getCollege());
            registrationForm2.setContact(registrationForm.getContact());
            registrationForm2.setEmail(registrationForm.getEmail());
            registrationForm2.setQualification(registrationForm.getQualification());
            this.registrationService.updateRegistrationForm(registrationForm2);
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("RegitrationFrom update Successfully !");
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


    @GetMapping("/registrations")
    public ResponseEntity<DataResponse> getRegistrationByManager(@RequestHeader("Authorization") String jwt){
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
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.searchRegistrationFormByName(name,manager));
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
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.searchRegistrationFormById(id,manager));
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


    @GetMapping("/searchRegistrationByBranch/{branch}")
    public ResponseEntity<DataResponse> searchRegistrationByBranch(@RequestHeader("Authorization") String jwt,@PathVariable("branch")String branch){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.searchRegistrationFormsByBranch(branch, manager));
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


    @GetMapping("/searchRegistrationByQualification/{quali}")
    public ResponseEntity<DataResponse> searchRegistrationByQualification(@RequestHeader("Authorization") String jwt,@PathVariable("quali")String quali){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
       
            response.setData(this.registrationService.searchRegistrationFormsByQualification(quali,manager));
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


    @GetMapping("/searchRegistrationByCollege/{college}")
    public ResponseEntity<DataResponse> searchRegistrationByCollege(@RequestHeader("Authorization") String jwt,@PathVariable("college")String college){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.searchRegistrationFormsByCollege(college,manager));
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


    @GetMapping("/searchRegistrationByCourse/{course}")
    public ResponseEntity<DataResponse> searchRegistrationByCourse(@RequestHeader("Authorization") String jwt,@PathVariable("course")String course){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.searchRegistrationFormsByCourse(course,manager));
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
    

    @GetMapping("/getDueForm")
    public ResponseEntity<DataResponse> getDueRegistrations(@RequestHeader("Authorization") String jwt){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        try{
            response.setData(this.registrationService.getDueRegistrationForms(manager));
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

    @GetMapping("/top5Registrations")
    public ResponseEntity<DataResponse> top5Registrations(@RequestHeader("Authorization") String jwt){
        DataResponse response = new DataResponse();
        Manager manager = this.managerService.getManagerByJwt(jwt);
        Pageable pageable = PageRequest.of(0, 5);
        try{
            response.setData(this.registrationService.top5RegistrationForms(manager, pageable));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("top 5 Registrations successfully !");
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