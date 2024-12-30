package com.management.customer_relation_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.customer_relation_management.entities.EnquiryDetail;


public interface EnquiryDetailRepo extends JpaRepository<EnquiryDetail,Long> {

    @Query("SELECT e FROM EnquiryDetail e WHERE e.enquiryForm.id =:id ORDER BY e.enquiryDate DESC")
    List<EnquiryDetail> findByEnquiryFormId(@Param("id")long id);
}
