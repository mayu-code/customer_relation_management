package com.management.customer_relation_management.service.serviceInterface;

import java.util.List;

import com.management.customer_relation_management.entities.Receipt;

public interface ReceiptService {
    
    Receipt addReceipt(Receipt receipt);
    List<Receipt> getReceipt();
    Receipt getReceiptById(long id);
}
