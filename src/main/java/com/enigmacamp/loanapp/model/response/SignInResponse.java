package com.enigmacamp.loanapp.model.response;

import com.enigmacamp.loanapp.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private String token;
    private List<Role> roles;
}
