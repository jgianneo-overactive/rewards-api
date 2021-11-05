package com.awards.service;

import com.awards.model.Customer;

public interface CustomerService {
    Customer createCustomer(String name);

    Customer getCustomer(Long id);

    Customer updateCustomer(Long id, String newName);

    Customer deleteCustomer(Long id);
}
