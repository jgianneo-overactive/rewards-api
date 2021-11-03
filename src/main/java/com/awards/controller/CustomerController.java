package com.awards.controller;

import com.awards.model.Customer;
import com.awards.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestParam String name) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> modifyCustomer(@PathVariable Long id, @RequestParam String name) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        return null;
    }
}
