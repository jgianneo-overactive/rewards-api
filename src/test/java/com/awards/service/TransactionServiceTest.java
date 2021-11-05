package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.controller.CreateTransactionRequest;
import com.awards.model.Customer;
import com.awards.model.Transaction;
import com.awards.repository.CustomerRepository;
import com.awards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Date;
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

    private Long id = 1L;
    private Customer customer = new Customer(id, "Jhon");
    private Date date = new Date(1541427104);
    private Float costValue = 100.0F;

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
        assertThrows(IllegalArgumentException.class, () -> service.insertTransaction(createTransactionRequest(id, null, date)), "Transaction cost value cannot be null");
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
        assertThrows(IllegalArgumentException.class, () -> service.insertTransaction(createTransactionRequest(id, null, date)), "Customer id cannot be null");
    }

    @Test
    void insertTransactionCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.insertTransaction(createTransactionRequest(id, costValue, date)), "Not found customer with id = 1");
    }

    @Test
    void getTransactionById() {

    }

    @Test
    void getTransactionByIdNotFound() {}

    @Test
    void getTransactionsByCustomerId() {}

    @Test
    void getTransactionByCustomerIdNotFouns() {}

    private CreateTransactionRequest createTransactionRequest(Long id, Float costValue, Date date) {
        return new CreateTransactionRequest(date, id, costValue);
    }
}
