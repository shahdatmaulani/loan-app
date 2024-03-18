package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.model.request.AuthRequest;
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

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest authRequest){
        SignUpResponse signUpResponse = authService.signUp(authRequest);
        CommonResponse<SignUpResponse> response = CommonResponse.<SignUpResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Register User Admin Success")
                .data(signUpResponse)
                .build();
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatusCode()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        SignInResponse signInResponse = authService.signIn(authRequest);
        CommonResponse<SignInResponse> response = new CommonResponse<>();
        if ( signInResponse != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Sign In Success");
            response.setData(signInResponse);
        } else {
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
            response.setMessage("Sign In Failed");
            response.setData(null);
        }
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatusCode()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
