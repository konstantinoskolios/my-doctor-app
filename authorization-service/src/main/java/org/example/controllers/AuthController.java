package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.AuthRequest;
import org.example.entity.UserCredential;
import org.example.service.AuthService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UnknownFormatConversionException;


@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody AuthRequest user) {
        return authService.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        boolean isAuthenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())).isAuthenticated();
        if (isAuthenticated) return authService.generateToken(authRequest.getUsername());
        throw new AuthenticationCredentialsNotFoundException("Not correct credentials provided user: " + authRequest.getUsername());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Valid token";
    }
}
