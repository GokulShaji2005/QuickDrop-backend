package com.quickdrop.quickdrop.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickdrop.quickdrop.auth.dto.AuthResponseDto;
import com.quickdrop.quickdrop.auth.dto.OtpRequestDto;
import com.quickdrop.quickdrop.auth.dto.OtpVerifyDto;
import com.quickdrop.quickdrop.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Step 1 — client sends mobile number to receive OTP
    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@Valid @RequestBody OtpRequestDto dto) {
        authService.requestOtp(dto.getMobile());
        return ResponseEntity.ok("OTP sent successfully");
    }

    // Step 2 — client sends mobile + OTP to receive JWT
    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponseDto> verifyOtp(@Valid @RequestBody OtpVerifyDto dto) {
        AuthResponseDto response = authService.verifyOtp(dto.getMobile(), dto.getOtpCode());
        return ResponseEntity.ok(response);
    }
}
