package com.awards.service;

import com.awards.model.Customer;
import com.awards.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository customerRepository;

    private String name = "Jhon";
    private Long id = 1L;

    @Test
    void insertCustomer() {
      Customer response = new Customer(name);
      when(customerRepository.save(any())).thenReturn(response);
      Customer newCustomer = service.createCustomer(name);
      assertEquals("Jhon", newCustomer.getName());
    }

    @Test
    void insertCustomerNullName() {
        String name = null;
        assertThrows(IllegalArgumentException.class, () -> service.createCustomer(name), "Name cannot be null");
    }

    @Test
    void getCustomerById() {
      when(customerRepository.getById(id)).thenReturn(new Customer(id, name));
      Customer newCustomer = service.getCustomer(id);
      assertEquals("Jhon", newCustomer.getName());
      assertEquals(1L, newCustomer.getId());
    }

    @Test
    void getCustomerByIdNotFound() {
        when(customerRepository.getById(id)).thenReturn(new Customer(id, name));
        Customer newCustomer = service.getCustomer(id);
        assertEquals("Jhon", newCustomer.getName());
        assertEquals(1L, newCustomer.getId());
    }
/*
    @Test
    public updateCustomer() {

    }*/
}
