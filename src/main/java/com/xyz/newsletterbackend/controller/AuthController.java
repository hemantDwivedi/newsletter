package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.dto.LoginDto;
import com.xyz.newsletterbackend.dto.RegisterDto;
import com.xyz.newsletterbackend.service.AuthService;
import com.xyz.newsletterbackend.security.JwtAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String bearerToken){
        authService.logoutUser(bearerToken);
        return ResponseEntity.ok("You are logged out successfully!");
    }
}