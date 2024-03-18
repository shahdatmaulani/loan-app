package com.enigmacamp.loanapp.service.impl;

import com.enigmacamp.loanapp.model.entity.AppUser;
import com.enigmacamp.loanapp.model.entity.User;
import com.enigmacamp.loanapp.repository.UserRepository;
import com.enigmacamp.loanapp.service.UserService;
import com.enigmacamp.loanapp.util.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserid(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Invalid credential User"));
        List<ERole> userRoles = new ArrayList<>();
        user.getRoles().forEach(role -> userRoles.add(ERole.valueOf(role.getRoles().name())));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(userRoles)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Invalid credential User"));
        List<ERole> userRoles = new ArrayList<>();
        user.getRoles().forEach(role -> userRoles.add(ERole.valueOf(role.getRoles().name())));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(userRoles)
                .build();
    }
}
