package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer findByIdCustomerIsDeleted(String id);
    List<Customer> getAllCustomer();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(String id);
}
