package com.awards.service;

import com.awards.model.Customer;
import com.awards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(String name) {
        if(Objects.isNull(name)){
           throw new IllegalArgumentException("Name cannot be null");
        }
        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.getById(id);
    }
}
