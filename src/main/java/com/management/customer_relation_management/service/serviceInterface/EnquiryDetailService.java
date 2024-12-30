package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.EnquiryDetail;
import com.management.customer_relation_management.entities.EnquiryForm;

public interface EnquiryDetailService {
    
    EnquiryDetail addEnquiryDetail(EnquiryDetail detail,EnquiryForm enquiryForm);
    void deleteEnquiryDetail(long id);

    List<EnquiryDetail> getAllEnquiryDetailByEnquiryForm(long id);
}
