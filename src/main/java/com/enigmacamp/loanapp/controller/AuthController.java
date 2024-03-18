package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.model.request.AuthRequest;
import com.enigmacamp.loanapp.model.request.CustomerInputDTO;
import com.enigmacamp.loanapp.model.response.CommonResponse;
import com.enigmacamp.loanapp.model.response.SignInResponse;
import com.enigmacamp.loanapp.model.response.SignUpResponse;
import com.enigmacamp.loanapp.service.AuthService;
import com.enigmacamp.loanapp.util.constant.ApiPathConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.API + ApiPathConstant.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup/admin")
    public ResponseEntity<?> signUpAdmin(@RequestBody AuthRequest authRequest){
        SignUpResponse signUpResponse = authService.signUpAdmin(authRequest);
        CommonResponse<SignUpResponse> response = CommonResponse.<SignUpResponse>builder()
                .message("Register User Admin Success")
                .data(signUpResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<?> signUpCustomer(@RequestBody CustomerInputDTO authRequest){
        SignUpResponse signUpResponse = authService.signUpCustomer(authRequest);
        CommonResponse<SignUpResponse> response = CommonResponse.<SignUpResponse>builder()
                .message("Register User Customer Success")
                .data(signUpResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        SignInResponse signInResponse = authService.signIn(authRequest);
        CommonResponse<SignInResponse> response = new CommonResponse<>();
        if ( signInResponse != null) {
            response.setMessage("Sign In Success");
            response.setData(signInResponse);
            return ResponseEntity.status(HttpStatus.OK.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setMessage("Sign In Failed");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

}
