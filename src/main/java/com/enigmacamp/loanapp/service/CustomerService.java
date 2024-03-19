package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    CustomerResponse findCustomerById(String id);
    CustomerResponse findByIdCustomerIsDeleted(String id);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomer(Customer customer);
    void deleteCustomer(String id);
}
