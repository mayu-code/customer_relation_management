package com.management.customer_relation_management.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Course;
import com.management.customer_relation_management.entities.RegistrationForm;
import com.management.customer_relation_management.service.serviceImpl.EmailServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.RegistrationServiceImpl;

@Service
public class MailFomater {
    
    @Autowired
    RegistrationServiceImpl registrationService;

    @Autowired
    EmailServiceImpl emailService;

    public static String generateRegistrationEmail(RegistrationForm registrationForm) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(registrationForm.getName()).append(",<br><br>");
        emailContent.append("Congratulations! Your registration with <b>Gradient Infotech</b> has been successfully completed. Below are your registration details:<br><br>");
        
        emailContent.append("<b>Registration Details:</b><br>");
        emailContent.append("- Registration ID: ").append(registrationForm.getId()).append("<br>");
        emailContent.append("- Registration Date: ").append(registrationForm.getRegistrationDate()).append("<br>");
        emailContent.append("- Name: ").append(registrationForm.getName()).append("<br>");
        emailContent.append("- Contact: ").append(registrationForm.getContact()).append("<br>");
        emailContent.append("- Email: ").append(registrationForm.getEmail()).append("<br>");
        emailContent.append("- College: ").append(registrationForm.getCollege()).append("<br>");
        emailContent.append("- Branch: ").append(registrationForm.getBranch()).append("<br>");
        emailContent.append("- Qualifications: ").append(registrationForm.getQualification()).append("<br>");
        emailContent.append("- Referred By: ").append(registrationForm.getSource()).append("<br><br>");
        
        emailContent.append("<b>Registered Courses:</b><br>");
        int i = 1;
        for (Course course : registrationForm.getRegisteredCourses()) {
            emailContent.append(String.valueOf(i) + ". <b>Course Name:</b> ").append(course.getCourseName()).append("<br>");
            emailContent.append("   - <b>Duration:</b> ").append(course.getCourseDuration()).append("<br>");
            emailContent.append("   - <b>Price:</b> Rs.").append(course.getPrice()).append("<br>");
            i++;
        }
        
        emailContent.append("<br><b>Payment Information:</b><br>");
        emailContent.append("- Total Fees: Rs.").append(registrationForm.getTotalFees()).append("<br>");
        emailContent.append("- Amount Paid: Rs.").append(registrationForm.getAmountPaid()).append("<br>");
        emailContent.append("- Payment Method: ").append(registrationForm.getPaymentType()).append("<br><br>");
    
        emailContent.append("We are excited to have you on board and look forward to helping you achieve your goals. If you have any questions or need assistance, feel free to contact us.<br><br>");
        emailContent.append("Warm Regards,<br>[Your Organization Name]<br>");
    
        return emailContent.toString();
    }
    
    

public void mail(long id){
    RegistrationForm registrationForm = this.registrationService.getRegistrationFormById(id);
    String body = generateRegistrationEmail(registrationForm);
    String subject = "Subject: Registration Successful! Welcome to Gradient Infotech";
    emailService.sendHtmlEmail(registrationForm.getEmail(), subject, body);
}
}
