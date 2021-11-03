package com.awards.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;
/*
    @Test
    public void insertCustomer() {
      Customer newCustomer = service.createCustomer("Juan");
      assertEquals("Juan", newCustomer.getName());
    }

    @Test
    public getCustomerById() {
      int id = 1;
      when(service.getCustomer(id)).thenReturn(new Customer("Juan"));
      Customer newCustomer = service.getCustomer(id);
      assertEquals("Juan", newCustomer.getName());
    }

    @Test
    public updateCustomer() {

    }*/
}
