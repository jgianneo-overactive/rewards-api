package com.awards.service;

import com.awards.controller.CreateTransactionRequest;
import com.awards.model.PointsCustomerReport;
import com.awards.model.Transaction;
import java.util.List;

public interface TransactionService {
    Transaction insertTransaction(CreateTransactionRequest request);

    Transaction getTransactionById(Long id);

    List<Transaction> getTransactionsByCustomerId(Long id);

    List<PointsCustomerReport> generatePointsCustomerReport(Integer methodIndex);
}
