package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.model.response.CommonResponse;
import com.enigmacamp.loanapp.model.response.CustomerResponse;
import com.enigmacamp.loanapp.service.CustomerService;
import com.enigmacamp.loanapp.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.AUTH)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customer/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        CustomerResponse result = customerService.findCustomerById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Data is found")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("customer/active/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> findByIdCustomerIsDeleted(@PathVariable String id) {
        CustomerResponse result = customerService.findByIdCustomerIsDeleted(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Data is found")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("customer/all")
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer() {
        List<CustomerResponse> result = customerService.getAllCustomer();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .message("Successfully fetch user")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @PutMapping("customer/update")
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody Customer customer) {
        Customer result = customerService.updateCustomer(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message("Successfully update user")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Successfully delete user")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


}
