package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.controller.CreateTransactionRequest;
import com.awards.model.Customer;
import com.awards.model.PointsCustomerReport;
import com.awards.model.Transaction;
import com.awards.repository.CustomerRepository;
import com.awards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private Logger log = Logger.getLogger("TransactionServiceImpl");

    @Override
    public Transaction insertTransaction(CreateTransactionRequest request) {
        if (Objects.isNull(request.getCostValue())) {
            throw new IllegalArgumentException("Transaction cost value cannot be null");
        }
        Date date;
        if (Objects.isNull(request.getDate())) {
            log.info("request.getDate() = null, Initializating date to current");
            date = new Date();
        } else {
            date = request.getDate();
        }
        if (Objects.isNull(request.getCustomerId())) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }
        Customer customer = getCustomerIfExists(request.getCustomerId());
        log.info("Found customer with id = " + request.getCustomerId());

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

    @Override
    public List<PointsCustomerReport> generatePointsCustomerReport() {
        List<Customer> customerList = customerRepository.findAll();
        log.info("Obtained customer list");
        return customerList.stream()
                .map(this::generateCustomerReport)
                .collect(Collectors.toList());
    }

    private PointsCustomerReport generateCustomerReport(Customer customer) {
        log.info("Calculating points for customer: " + customer.getId());
        Integer calculatedPoints = transactionRepository
                .getLastThreeMonthsTransacionsByCustomerId(customer.getId())
                .stream()
                .mapToInt(t -> calculatePoint(t.getCost()))
                .sum();
        log.info("Generating report for customer: " + customer.getId());
        return new PointsCustomerReport(customer, calculatedPoints);
    }

    private Integer calculatePoint(float floatValue) {
        Integer value = (int) floatValue;
        if (value > 100) {
            log.info("(" + value + " - 100)*2 + 50");
            return (value - 100)*2 + 50;
        } else if (value > 50) {
            log.info(value + " - 50");
            return value - 50;
        }
        return 0;
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
