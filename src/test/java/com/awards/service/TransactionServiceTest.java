package com.awards.service;

import com.awards.common.exception.ProcessException;
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
    void getNormalPointsCustomerReport_2() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(new Customer(2L,"James"));
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 90.5F , new Date()));
        transactionList.add(new Transaction(customer, 99.90F , new Date()));
        transactionList.add(new Transaction(customer, 49.2F , date));
        List<Transaction> transactionList2 = new ArrayList<>();
        transactionList.add(new Transaction(customer, 49.2F , date));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(2L)).thenReturn(transactionList2);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport(2);
        assertEquals(89, report.get(0).getPoints());
        assertEquals(89, report.get(0).getLastMonthPoints());
        assertEquals(0, report.get(1).getPoints());
    }

    @Test
    void getNormalPointsCustomerReport_1() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 49.9999F , date));
        transactionList.add(new Transaction(customer, 49.321F , date));
        transactionList.add(new Transaction(customer, 49.0F , date));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport(2);
        assertEquals(0, report.get(0).getPoints());
    }
    @Test
    void getNoPointsCustomerReport_3() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 120.821F , new Date()));
        transactionList.add(new Transaction(customer, 101.0001F , new Date()));
        transactionList.add(new Transaction(customer, 99.99F , new Date()));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport(0);
        assertEquals(0, report.get(0).getPoints());
        assertEquals(0, report.get(0).getLastMonthPoints());
    }
    @Test
    void getSimplePointsCustomerReport_3() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 120.821F , new Date()));
        transactionList.add(new Transaction(customer, 101.0001F , new Date()));
        transactionList.add(new Transaction(customer, 99.99F , new Date()));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport(1);
        assertEquals(170, report.get(0).getPoints());
        assertEquals(170, report.get(0).getLastMonthPoints());
    }
    @Test
    void getExtraPointsCustomerReport_3() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 220.821F , new Date()));
        transactionList.add(new Transaction(customer, 101.0001F , new Date()));
        transactionList.add(new Transaction(customer, 99.99F , new Date()));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        List<PointsCustomerReport> report = service.generatePointsCustomerReport(3);
        assertEquals(411, report.get(0).getPoints());
        assertEquals(411, report.get(0).getLastMonthPoints());
    }
    @Test
    void getDefaultPointsCustomerReportNullMethodId() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 120.821F , new Date()));
        transactionList.add(new Transaction(customer, 101.0001F , new Date()));
        transactionList.add(new Transaction(customer, 99.99F , new Date()));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        assertThrows(ProcessException.class, () -> service.generatePointsCustomerReport(null));
    }
    @Test
    void getPointsCustomerReportMethodNoExist() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(customer, 220.821F , new Date()));
        when(transactionRepository.getLastThreeMonthsTransacionsByCustomerId(1L)).thenReturn(transactionList);
        assertThrows(IllegalArgumentException.class, () -> service.generatePointsCustomerReport(10));
    }
    private CreateTransactionRequest createTransactionRequest(Long id, Float costValue, Date date) {
        return new CreateTransactionRequest(date, id, costValue);
    }

    private void insertTransaction(Long id, Float costValue, Date date) {
        service.insertTransaction(createTransactionRequest(id, costValue, date));
    }
}
