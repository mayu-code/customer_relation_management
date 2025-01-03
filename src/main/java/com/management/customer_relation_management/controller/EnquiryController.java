package com.management.customer_relation_management.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.EnquiryDetail;
import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.response.DataResponse;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.service.serviceImpl.CourseServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.EnquiryDetailServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.EnquiryFormServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class EnquiryController {

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @Autowired
    private EnquiryFormServiceImpl EnquiryFormService;

    @Autowired
    CourseServiceImpl courseSErvice;

    @Autowired
    EnquiryDetailServiceImpl enquiryDetailService;


    @PostMapping("/addEnquiry")
    public ResponseEntity<SuccessResponse> addEnquiry(@RequestHeader("Authorization") String jwt,@RequestBody EnquiryForm enquiryForm){
        Manager manager = this.managerServiceImpl.getManagerByJwt(jwt);
        EnquiryForm enquiryForm2 = new EnquiryForm();
        SuccessResponse response = new SuccessResponse();
        EnquiryForm isForm = this.EnquiryFormService.getEnquiryFormByEmail(enquiryForm.getEmail());
        if(isForm!=null){
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry Form exits please enter a another email !");
            return ResponseEntity.of(Optional.of(response));
        }
        enquiryForm.setEnquiryDate(DateTimeFormatter.format(LocalDateTime.now()));
        List<Course> courses = enquiryForm.getCourses();
        try{
            enquiryForm2 = this.EnquiryFormService.addEnquiryForm(enquiryForm, manager);
            for(Course course:courses){
                System.out.println(course.toString());
                this.courseSErvice.addEnquiryCourse(course, enquiryForm2);
            }
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(200);
            response.setMessage("Enquiry Added Succesfully !");
            return ResponseEntity.of(Optional.of(response));
        }
        catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setMessage("Something Went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/updateEnquiry")
    public ResponseEntity<SuccessResponse> updateEnquiry(@RequestBody EnquiryForm enquiryForm){

        SuccessResponse response = new SuccessResponse();
        EnquiryForm updateEnquiry = this.EnquiryFormService.getEnquiryFormById(enquiryForm.getId());
        if(!updateEnquiry.getEmail().equals(enquiryForm.getEmail())){
            EnquiryForm isPresentByEmail = this.EnquiryFormService.getEnquiryFormByEmail(enquiryForm.getEmail());
            if(!(isPresentByEmail==null)){
            response.setStatus(HttpStatus.ALREADY_REPORTED);
            response.setStatusCode(200);
            response.setMessage("Email already present");
            return ResponseEntity.of(Optional.of(response));
        }
        }
        updateEnquiry.setBranch(enquiryForm.getBranch());
        updateEnquiry.setCollege(enquiryForm.getCollege());
        updateEnquiry.setQualification(enquiryForm.getQualification());
        updateEnquiry.setEmail(enquiryForm.getEmail());
        updateEnquiry.setContact(enquiryForm.getContact());
        updateEnquiry.setName(enquiryForm.getName());
        EnquiryForm enquiryForm2 = new EnquiryForm();
        List<Course> courses = enquiryForm.getCourses();

        try{
            enquiryForm2 = this.EnquiryFormService.updateEnquiryForm(updateEnquiry);
            this.courseSErvice.deleteCourses(enquiryForm.getId());
            for(Course course:courses){
                this.courseSErvice.addEnquiryCourse(course, enquiryForm2);
            }
            response.setStatus(HttpStatus.ACCEPTED);
            response.setStatusCode(200);
            response.setMessage("Enquiry update Succesfully !");
            return ResponseEntity.of(Optional.of(response));
        }
        catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(500);
            response.setMessage("Something Went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/enquiries")
    public ResponseEntity<DataResponse> getEnquiryByManager(@RequestHeader("Authorization") String jwt){
        Manager manager  = this.managerServiceImpl.getManagerByJwt(jwt);
        DataResponse response = new DataResponse();
        try{
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("enquiryform get succusfully !");
            response.setData(this.EnquiryFormService.enquiryFormByManager(manager));
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/deleteEnquiry/{id}")
    public ResponseEntity<DataResponse> deleteEnquiry(@PathVariable("id") long id){
        DataResponse response = new DataResponse();
        try{
            this.EnquiryFormService.deleteEnquiry(id);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("delete Enquiry successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/getEnquiryById/{id}")
    public ResponseEntity<DataResponse> getEnquiryById(@PathVariable("id")long id){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.EnquiryFormService.getEnquiryFormById(id));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Get Enquiry successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/addEnquiryDetail/{id}")
    public ResponseEntity<DataResponse> addEnquiryDetail(@PathVariable("id")long id ,@RequestBody EnquiryDetail detail){
        DataResponse response = new DataResponse();
        detail.setEnquiryDate(DateTimeFormatter.format(LocalDateTime.now()));
        EnquiryForm enquiryForm = this.EnquiryFormService.getEnquiryFormById(id);
        try{
            response.setData(this.enquiryDetailService.addEnquiryDetail(detail,enquiryForm ));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Add Enquiry Detail successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getEnquiryDetail/{id}")
    public ResponseEntity<DataResponse> getEnquiryDetail(@PathVariable("id")long id){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.enquiryDetailService.getAllEnquiryDetailByEnquiryForm(id));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("get enquiry details successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/deleteEnquiryDetail/{id}")
    public ResponseEntity<DataResponse> deleteEnquiryDetail(@PathVariable("id")long id){
        DataResponse response = new DataResponse();
        try{
            this.enquiryDetailService.deleteEnquiryDetail(id);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Delete Enquiry Detail successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/getEnquiryByCourseName/{courseName}")
    public ResponseEntity<DataResponse> getEnquiryFormByCourseName(@RequestHeader("Authorization")String jwt,@PathVariable("courseName") String course){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.getEnquiryFormsByCourseName(course,manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchEnquiryFormById/{id}")
    public ResponseEntity<DataResponse> searchBEnquiryFormById(@RequestHeader("Authorization")String jwt,@PathVariable("id") String id){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.searchBEnquiryFormById(id, manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchEnquiryFormByName/{name}")
    public ResponseEntity<DataResponse> searchBEnquiryFormByName(@RequestHeader("Authorization")String jwt,@PathVariable("name") String name){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.searchBEnquiryFormByName(name, manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchEnquiryFormByBranch/{branch}")
    public ResponseEntity<DataResponse> searchBEnquiryFormByBranch(@RequestHeader("Authorization")String jwt,@PathVariable("branch") String branch){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.searchBEnquiryFormByBranch(branch, manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchEnquiryFormByCollege/{college}")
    public ResponseEntity<DataResponse> searchBEnquiryFormByCollege(@RequestHeader("Authorization")String jwt,@PathVariable("college") String college){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.searchBEnquiryFormByCollege(college, manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/searchEnquiryFormByQualification/{quali}")
    public ResponseEntity<DataResponse> searchBEnquiryFormByQualification(@RequestHeader("Authorization")String jwt,@PathVariable("quali") String quali){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        try{
            response.setData(this.EnquiryFormService.searchBEnquiryFormByQualification(quali,manager));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("Enquiry forms get successfully !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(505);
            response.setMessage("something went wrong !");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getTop5EnquiryForm")
    public ResponseEntity<DataResponse> top5EnquiryForm(@RequestHeader("Authorization")String jwt){
        DataResponse response = new DataResponse();
        Manager manager = managerServiceImpl.getManagerByJwt(jwt);
        Pageable pageable = PageRequest.of(0, 5);
        try{
            response.setData(this.EnquiryFormService.top5EnquiryForms(manager, pageable));
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(200);
            response.setMessage("top 5 enquiry form get successfully !");
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
