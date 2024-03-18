package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.entity.AppUser;
import com.enigmacamp.loanapp.model.response.SignUpResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserid(String id);
    SignUpResponse findUserById(String id);

}
