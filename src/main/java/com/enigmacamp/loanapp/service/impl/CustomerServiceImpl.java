package com.enigmacamp.loanapp.service.impl;

import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.repository.CustomerRepository;
import com.enigmacamp.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer findByIdCustomerIsDeleted(String id) {
        return customerRepository.findByIdCustomerIsDeleted(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customerRepository.findById(customer.getId()).isPresent()) {
            return saveCustomer(customer);
        } else {
            throw new RuntimeException("Product id : " + customer.getId() + " not found");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
