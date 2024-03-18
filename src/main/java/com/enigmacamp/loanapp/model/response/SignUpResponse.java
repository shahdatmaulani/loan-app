package com.enigmacamp.loanapp.model.response;

import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.util.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse {
    private String email;
    private List<ERole> role;
}
