package com.quickdrop.quickdrop.auth.service;

import org.springframework.stereotype.Service;

import com.quickdrop.quickdrop.auth.dto.AuthResponseDto;
import com.quickdrop.quickdrop.common.exceptions.InvalidInputException;
import com.quickdrop.quickdrop.identity.entity.Person;
import com.quickdrop.quickdrop.identity.service.PersonService;

@Service
public class AuthService {

    private final OtpService otpService;
    private final JwtService jwtService;
    private final PersonService personService;

    public AuthService(OtpService otpService, JwtService jwtService, PersonService personService) {
        this.otpService = otpService;
        this.jwtService = jwtService;
        this.personService = personService;
    }

    // Step 1 — called when user requests OTP
    public void requestOtp(String mobile) {
        if (mobile == null || mobile.isBlank()) {
            throw new InvalidInputException("Mobile number must not be blank");
        }

        // Generate OTP, save to Redis with 5 min TTL & 60s cooldown, return the OTP string
        String otp = otpService.saveOtp(mobile);

        // In production: send via SMS gateway
        // For now: log to console for development/testing
        System.out.println("OTP for " + mobile + " : " + otp);
    }


    // Step 2 — called when user submits the OTP they received
    public AuthResponseDto verifyOtp(String mobile, String enteredOtp) {
        if (mobile == null || mobile.isBlank()) {
            throw new InvalidInputException("Mobile number must not be blank");
        }
        if (enteredOtp == null || enteredOtp.isBlank()) {
            throw new InvalidInputException("OTP must not be blank");
        }

        // Validates OTP against Redis — throws InvalidInputException if wrong/expired
        otpService.verifyOtp(mobile, enteredOtp);

        // OTP is valid — get the Person from DB
        Person person = personService.findOrCreateByMobile(mobile);

        // Generate JWT using the person's ID
        String token = jwtService.generateToken(person.getId());

        // Wrap and return the token as a response DTO
        return new AuthResponseDto(token);
    }
}
