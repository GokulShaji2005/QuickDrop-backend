package com.quickdrop.quickdrop.identity.controller;

import com.quickdrop.quickdrop.identity.dto.OnboardRequestDto;
import com.quickdrop.quickdrop.identity.dto.UserProfileDto;
import com.quickdrop.quickdrop.identity.service.UserService;
import com.quickdrop.quickdrop.vehicle.dto.VehicleTypeResponseDto;
import com.quickdrop.quickdrop.vehicle.service.VehicleTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final VehicleTypeService vehicleTypeService;

    public UserController(UserService userService, VehicleTypeService vehicleTypeService) {
        this.userService = userService;
        this.vehicleTypeService = vehicleTypeService;
    }

    // Public endpoint for dropdown list of vehicle types with nested models
    @GetMapping("/vehicle-types")
    public ResponseEntity<List<VehicleTypeResponseDto>> getVehicleTypes() {
        return ResponseEntity.ok(vehicleTypeService.getActiveVehicleTypesWithModels());
    }

    // Get current authenticated user profile
    @GetMapping("/user/me")
    public ResponseEntity<UserProfileDto> getMyProfile() {
        Long personId = getAuthenticatedPersonId();
        return ResponseEntity.ok(userService.getProfile(personId));
    }

    // Submit onboarding details (Role, Name, Driver details if applicable)
    @PatchMapping("/user/onboard")
    public ResponseEntity<UserProfileDto> onboard(@Valid @RequestBody OnboardRequestDto dto) {
        Long personId = getAuthenticatedPersonId();
        return ResponseEntity.ok(userService.onboard(personId, dto));
    }

    private Long getAuthenticatedPersonId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Long) {
            return (Long) principal;
        }
        return Long.parseLong(principal.toString());
    }
}
