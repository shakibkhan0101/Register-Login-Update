package com.globalitgeeks.examninja.LoginAndRegisterUser.controller;

import com.globalitgeeks.examninja.LoginAndRegisterUser.dto.UserDTO;
import com.globalitgeeks.examninja.LoginAndRegisterUser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Login API
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO request) {
        boolean authenticated = authService.loginUser(request);
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Registration API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO request) {
        authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Forgot Password API
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody UserDTO request) {
        authService.updatePassword(request);
        return ResponseEntity.ok("Password updated successfully");
    }
}
