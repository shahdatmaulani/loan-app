package com.enigmacamp.loanapp.service.impl;

import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.model.response.CustomerResponse;
import com.enigmacamp.loanapp.repository.CustomerRepository;
import com.enigmacamp.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        Customer customer =  customerRepository.findById(id).get();
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.isStatus())
                .build();
    }

    @Override
    public CustomerResponse findByIdCustomerIsDeleted(String id) {
        Customer customer = customerRepository.findByIdCustomerIsDeleted(id);
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.isStatus())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
       List<Customer> customerList = customerRepository.findAll();
       List<CustomerResponse> responseList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerResponse response = CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .phone(customer.getPhone())
                    .status(customer.isStatus())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public CustomerResponse updateCustomer(Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        Date localDate = customer.getDateOfBirth();
        if (updatedCustomer != null) {
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setDateOfBirth(localDate);
            updatedCustomer.setStatus(customer.isStatus());
            customerRepository.save(customer);
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .phone(customer.getPhone())
                    .status(customer.isStatus())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
