package com.management.customer_relation_management.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Receipt;
import com.management.customer_relation_management.repository.ReceiptRepo;
import com.management.customer_relation_management.service.serviceInterface.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    ReceiptRepo receiptRepo;

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return this.receiptRepo.save(receipt);
    }

    @Override
    public List<Receipt> getReceipt() {
        return this.receiptRepo.findAll();
    }

    @Override
    public Receipt getReceiptById(long id) {
        return this.receiptRepo.findById(id).get();
    }
    
}
