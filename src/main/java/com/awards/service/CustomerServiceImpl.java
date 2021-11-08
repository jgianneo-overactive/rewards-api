package com.awards.service;

import com.awards.common.exception.ResourceNotFound;
import com.awards.model.Customer;
import com.awards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(String name) {
        notNullName(name);
        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return getCustomerIfExists(id);
    }

    @Override
    public Customer updateCustomer(Long id, String newName) {
        notNullName(newName);
        Customer customer = getCustomerIfExists(id);
        customer.setName(newName);
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = getCustomerIfExists(id);
        //customerRepository.delete(customer); TODO change to logical delete
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    private boolean notNullName(String name) {
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Name cannot be null");
        }
        return true;
    }

    private Customer getCustomerIfExists(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()) {
            throw new ResourceNotFound("Not found customer with id = " + id);
        }
        return customer.get();
    }

}
