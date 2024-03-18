package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.service.CustomerService;
import com.enigmacamp.loanapp.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.AUTH)
public class CustomerController {
    private final CustomerService customerService;
}
