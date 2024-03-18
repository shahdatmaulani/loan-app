package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.util.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoles(ERole role);
}

