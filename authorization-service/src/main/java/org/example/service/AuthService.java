package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Utility.JwtUtil;
import org.example.dto.AuthRequest;
import org.example.entity.UserCredential;
import org.example.repository.UserCredentialRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String saveUser(AuthRequest request) {
        userCredentialRepository.save(UserCredential
                .builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return String.format("User added: %s", request.getUsername());
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }
}
