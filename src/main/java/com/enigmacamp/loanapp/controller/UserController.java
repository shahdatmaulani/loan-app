package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.model.entity.User;
import com.enigmacamp.loanapp.model.response.CommonResponse;
import com.enigmacamp.loanapp.model.response.SignUpResponse;
import com.enigmacamp.loanapp.service.UserService;
import com.enigmacamp.loanapp.util.constant.ApiPathConstant;
import com.enigmacamp.loanapp.util.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.AUTH)
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<SignUpResponse>> getCustomerById(@PathVariable String id) {
        SignUpResponse result = userService.findUserById(id);
        CommonResponse<SignUpResponse> response = CommonResponse.<SignUpResponse>builder()
                .message("Data is found")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

}
