package com.enigmacamp.loanapp.service.impl;

import com.enigmacamp.loanapp.model.entity.AppUser;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.model.entity.User;
import com.enigmacamp.loanapp.model.request.AuthRequest;
import com.enigmacamp.loanapp.model.response.SignInResponse;
import com.enigmacamp.loanapp.model.response.SignUpResponse;
import com.enigmacamp.loanapp.repository.UserRepository;
import com.enigmacamp.loanapp.security.JwtUtil;
import com.enigmacamp.loanapp.service.AuthService;
import com.enigmacamp.loanapp.service.RoleService;
import com.enigmacamp.loanapp.util.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signUpAdmin(AuthRequest authRequest) {
        try {
            List<Role> roles = roleService.getOrSave(ERole.ROLE_ADMIN, ERole.ROLE_STAFF);
            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            List<ERole> eRoles = new ArrayList<>();
            for (Role role : roles) {
                eRoles.add(role.getRoles());
            }
            return SignUpResponse.builder()
                    .email(user.getEmail())
                    .roles(eRoles)
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Override
    public SignUpResponse signUpCustomer(AuthRequest authRequest) {
        try {
            List<Role> roles = roleService.getOrSave(ERole.ROLE_CUSTOMER);
            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            List<ERole> eRoles = new ArrayList<>();
            for (Role role : roles) {
                eRoles.add(role.getRoles());
            }
            return SignUpResponse.builder()
                    .email(user.getEmail())
                    .roles(eRoles)
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Override
    public SignInResponse signIn(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AppUser appUser = (AppUser) authentication.getPrincipal();
            String token = jwtUtil.generateToken(appUser);
            return SignInResponse.builder()
                    .email(appUser.getEmail())
                    .roles(appUser.getRoles())
                    .token(token)
                    .build();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
