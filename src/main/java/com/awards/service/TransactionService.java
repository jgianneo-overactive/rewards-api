package com.awards.service;

import com.awards.controller.CreateTransactionRequest;
import com.awards.model.Transaction;

public interface TransactionService {
    Transaction insertTransaction(CreateTransactionRequest request);
}
