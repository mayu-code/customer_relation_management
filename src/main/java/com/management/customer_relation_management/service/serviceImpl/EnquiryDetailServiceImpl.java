package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.EnquiryDetail;
import com.management.customer_relation_management.entities.EnquiryForm;
import com.management.customer_relation_management.repository.EnquiryDetailRepo;
import com.management.customer_relation_management.service.serviceInterface.EnquiryDetailService;

@Service
public class EnquiryDetailServiceImpl implements EnquiryDetailService{

    @Autowired
    EnquiryDetailRepo detailRepo;


    @Override
    public EnquiryDetail addEnquiryDetail(EnquiryDetail detail, EnquiryForm enquiryForm) {
        detail.setEnquiryForm(enquiryForm);
        return this.detailRepo.save(detail);
    }

    @Override
    public void deleteEnquiryDetail(long id) {
        this.detailRepo.deleteById(id);
        return;
    }

    @Override
    public List<EnquiryDetail> getAllEnquiryDetailByEnquiryForm(long id) {
        return this.detailRepo.findByEnquiryFormId(id);
    }
    
}
