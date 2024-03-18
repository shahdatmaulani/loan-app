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
import jakarta.transaction.Transactional;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signUp(AuthRequest authRequest) {
        try {
            List<Role> roles = roleService.getOrSave(ERole.admin, ERole.staff);
            User user = User.builder()
                    .email(authRequest.getEmail())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            List<ERole> eRoles = roles.stream()
                    .map(Role::getRole)
                    .collect(Collectors.toList());
            return SignUpResponse.builder()
                    .email(user.getEmail())
                    .role(eRoles)
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
                    .token(token)
                    .roles(appUser.getRoles())
                    .build();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
