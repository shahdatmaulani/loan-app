package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.util.constant.ERole;

import java.util.List;

public interface RoleService {
    List<Role> getOrSave(ERole... roles);

}
