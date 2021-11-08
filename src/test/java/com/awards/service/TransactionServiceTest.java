package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.controller.CreateTransactionRequest;
import com.awards.model.Customer;
import com.awards.model.PointsCustomerReport;
import com.awards.model.Transaction;
import com.awards.repository.CustomerRepository;
import com.awards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService service;

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private TransactionRepository transactionRepository;

    private final Long id = 1L;
    private final Customer customer = new Customer(id, "Jhon");
    private final Date date = new Date(1541427104);
    private final Float costValue = 100.0F;
    private final Transaction transaction = new Transaction(id, customer,costValue, date);

    @Test
    void insertTransaction() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.save(any())).thenReturn(new Transaction(customer, costValue, date));
        Transaction response = service.insertTransaction(createTransactionRequest(id, 100.0F, new Date(1541427104)));
        assertEquals(date, response.getDate());
        assertEquals(1L, response.getCustomer().getId());
        assertEquals(100.0F, response.getCost());
    }

    @Test
    void insertTransactionNoCostValue() {
        assertThrows(IllegalArgumentException.class, () -> insertTransaction(id, null, date), "Transaction cost value cannot be null");
    }

    @Test
    void insertTransactionNoDate() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.save(any())).thenReturn(new Transaction(customer, costValue, date));
        Transaction response = service.insertTransaction(createTransactionRequest(id, 100.0F, null));
        assertEquals(1L, response.getCustomer().getId());
        assertEquals(100.0F, response.getCost());
    }

    @Test
    void insertTransactionNoCustomerId() {
        assertThrows(IllegalArgumentException.class, () -> insertTransaction(id, null, date), "Customer id cannot be null");
    }

    @Test
    void insertTransactionCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> insertTransaction(id, costValue, date), "Not found customer with id = 1");
    }

    @Test
    void getTransactionById() {
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        Transaction response = service.getTransactionById(id);
        assertEquals(date, response.getDate());
        assertEquals(1L, response.getCustomer().getId());
        assertEquals(100.0F, response.getCost());
    }

    @Test
    void getTransactionByIdNotFound() {
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.getTransactionById(id), "Not found transaction with id = 1");
    }

    @Test
    void getTransactionsByCustomerId() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.getTransacionsByCustomerId(id)).thenReturn(transactionList);
        List<Transaction> response = service.getTransactionsByCustomerId(id);
        assertEquals(1, response.size());
    }

    @Test
    void getTransactionByCustomerIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.getTransactionsByCustomerId(id), "Not found customer with id = 1");
    }

    @Test
    void getPointsCustomerReport_1() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 120.0F , date));
        when(transactionRepository.getTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport();
        assertEquals(90, report.get(1).getPoints());
    }
    @Test
    void getPointsCustomerReport_2() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 90.0F , date));
        when(transactionRepository.getTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport();
        assertEquals(40, report.get(1).getPoints());
    }

    @Test
    void getPointsCustomerReport_3() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 40.0F , date));
        when(transactionRepository.getTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport();
        assertEquals(0, report.get(1).getPoints());
    }

    private CreateTransactionRequest createTransactionRequest(Long id, Float costValue, Date date) {
        return new CreateTransactionRequest(date, id, costValue);
    }

    private void insertTransaction(Long id, Float costValue, Date date) {
        service.insertTransaction(createTransactionRequest(id, costValue, date));
    }
}
