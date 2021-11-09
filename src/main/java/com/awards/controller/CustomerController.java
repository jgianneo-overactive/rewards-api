package com.awards.controller;

import com.awards.model.Customer;
import com.awards.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Api(value = "CustomerController", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/{id}")
    @ApiOperation("Get customer by id")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create customer by name")
    public ResponseEntity<Customer> createCustomer(@RequestParam String name) {
        return new ResponseEntity<>(service.createCustomer(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Modify customer name")
    public ResponseEntity<Customer> modifyCustomer(@PathVariable Long id, @RequestParam String name) {
        return new ResponseEntity<>(service.updateCustomer(id, name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete customer: not working")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteCustomer(id), HttpStatus.OK);
    }
}
