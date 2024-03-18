package com.enigmacamp.loanapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigmacamp.loanapp.model.entity.AppUser;
import com.enigmacamp.loanapp.util.constant.ERole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${app.enigma_camp.jwt.jwt-secret}")
    private String secretKey;
    @Value("${app.enigma_camp.jwt.app-name}")
    private String appName;
    @Value("${app.enigma_camp.jwt.jwt-expired}")
    private long expiredJwt;


    public String generateToken(AppUser appUser){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
        List<String> rolesAsString = new ArrayList<>();
        for (ERole role : appUser.getRoles()) {
            rolesAsString.add(role.name());
        }
        return JWT.create()
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Instant.now().plusMillis(expiredJwt))
                .withIssuedAt(Instant.now())
                .withArrayClaim("roles", rolesAsString.toArray(new String[0]))
                .sign(algorithm);
    }

    public boolean verifyJwtToken (String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getIssuer().equals(appName);
    }

    public Map<String, String> getUserInfoByToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            String rolesAsString = Arrays.stream(roles)
                    .map(ERole::valueOf)
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
            userInfo.put("roles", rolesAsString);
            return userInfo;
        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }

    }

}
