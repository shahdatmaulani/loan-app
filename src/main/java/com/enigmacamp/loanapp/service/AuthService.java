package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.request.AuthRequest;
import com.enigmacamp.loanapp.model.response.SignInResponse;
import com.enigmacamp.loanapp.model.response.SignUpResponse;

public interface AuthService {
    SignUpResponse signUpAdmin(AuthRequest authRequest);

    SignUpResponse signUpCustomer(AuthRequest authRequest);

    SignInResponse signIn(AuthRequest authRequest);

}
