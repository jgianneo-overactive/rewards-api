package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.controller.CreateTransactionRequest;
import com.awards.model.Customer;
import com.awards.model.Transaction;
import com.awards.repository.CustomerRepository;
import com.awards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transaction insertTransaction(CreateTransactionRequest request) {
        if (Objects.isNull(request.getCostValue())) {
            throw new IllegalArgumentException("Transaction cost value cannot be null");
        }
        Date date;
        if (Objects.isNull(request.getDate())) {
            date = new Date();
        } else {
            date = request.getDate();
        }
        if (Objects.isNull(request.getCustomerId())) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }
        Customer customer = getCustomerIfExists(request.getCustomerId());
        Transaction transaction = new Transaction(customer, request.getCostValue(), date);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return getTransactionIfExists(id);
    }

    @Override
    public List<Transaction> getTransactionsByCustomerId(Long id) {
        getCustomerIfExists(id);
        return transactionRepository.getTransacionsByCustomerId(id);
    }

    private Customer getCustomerIfExists(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()) {
            throw new ResourceNotFound("Not found customer with id = " + id);
        }
        return customer.get();
    }

    private Transaction getTransactionIfExists(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(!transaction.isPresent()) {
            throw new ResourceNotFound("Not found transaction with id = " + id);
        }
        return transaction.get();
    }
}
