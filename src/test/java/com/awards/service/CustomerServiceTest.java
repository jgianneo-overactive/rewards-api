package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.model.Customer;
import com.awards.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;

    private String name = "Jhon";
    private String newName = "Peter";
    private Long id = 1L;

    @Test
    void insertCustomer() {
        Customer response = new Customer(name);
        when(repository.save(any())).thenReturn(response);
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
        when(repository.findById(id)).thenReturn(Optional.of(new Customer(id, name)));
        Customer newCustomer = service.getCustomer(id);
        assertEquals("Jhon", newCustomer.getName());
        assertEquals(1L, newCustomer.getId());
    }

    @Test
    void getCustomerByIdNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.getCustomer(id), "Not found customer with id = 1");
    }

    @Test
    void updateCustomer() {
        when(repository.findById(id)).thenReturn(Optional.of(new Customer(id, name)));
        when(repository.save(any())).thenReturn(new Customer(id, newName));
        Customer updatedCustomer = service.updateCustomer(id, newName);
        assertEquals("Peter", updatedCustomer.getName());
        assertEquals(1L, updatedCustomer.getId());
    }

    @Test
    void updateCustomerNullName() {
        String newName = null;
        assertThrows(IllegalArgumentException.class, () -> service.updateCustomer(id, newName), "Name cannot be null");
    }

    @Test
    void updateCustomerNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.updateCustomer(id, newName), "Not found customer with id = 1");
    }

    @Test
    void deleteCustomer() {
        when(repository.findById(id)).thenReturn(Optional.of(new Customer(id, name)));
        Customer deletedCustomer = service.deleteCustomer(id);
        assertEquals("Jhon", deletedCustomer.getName());
        assertEquals(1L, deletedCustomer.getId());
    }

    @Test
    void deleteCustomerNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> service.deleteCustomer(id), "Not found customer with id = 1");
    }
}
