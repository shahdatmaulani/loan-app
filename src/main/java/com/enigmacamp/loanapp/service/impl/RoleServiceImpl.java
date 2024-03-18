package com.enigmacamp.loanapp.service.impl;

import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.service.RoleService;
import com.enigmacamp.loanapp.util.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getOrSave(ERole... roles) {
        List<Role> roleList = new ArrayList<>();
        for (ERole eRole : roles) {
            Optional<Role> optionalRole = roleRepository.findByRoles(eRole);
            Role role;
            if (optionalRole.isPresent()) {
                role = optionalRole.get();
            } else {
                role = new Role();
                role.setRoles(eRole);
                role = roleRepository.save(role);
            }
            roleList.add(role);
        }
        return roleList;
    }
}
