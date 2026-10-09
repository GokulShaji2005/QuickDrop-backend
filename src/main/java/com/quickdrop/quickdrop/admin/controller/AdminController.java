package com.quickdrop.quickdrop.admin.controller;

import com.quickdrop.quickdrop.admin.dto.AdminDriverResponseDto;
import com.quickdrop.quickdrop.admin.dto.DriverActionDto;
import com.quickdrop.quickdrop.admin.service.AdminService;
import com.quickdrop.quickdrop.identity.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/drivers")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Get list of drivers pending review
    @GetMapping("/pending")
    public ResponseEntity<Page<AdminDriverResponseDto>> getPendingDrivers(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(adminService.getPendingDrivers(pageable));
    }

    // List drivers by status (or all drivers if status is omitted)
    @GetMapping
    public ResponseEntity<Page<AdminDriverResponseDto>> getDrivers(
            @RequestParam(required = false) UserStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(adminService.getDriversByStatus(status, pageable));
    }

    // Approve driver account (status -> ACTIVE)
    @PatchMapping("/{userId}/approve")
    public ResponseEntity<AdminDriverResponseDto> approveDriver(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.approveDriver(userId));
    }

    // Reject driver account (status -> DEACTIVATED)
    @PatchMapping("/{userId}/reject")
    public ResponseEntity<AdminDriverResponseDto> rejectDriver(
            @PathVariable Long userId,
            @RequestBody(required = false) DriverActionDto dto) {
        String reason = dto != null ? dto.getReason() : null;
        return ResponseEntity.ok(adminService.rejectDriver(userId, reason));
    }

    // Suspend driver account (status -> SUSPENDED)
    @PatchMapping("/{userId}/suspend")
    public ResponseEntity<AdminDriverResponseDto> suspendDriver(
            @PathVariable Long userId,
            @RequestBody(required = false) DriverActionDto dto) {
        String reason = dto != null ? dto.getReason() : null;
        return ResponseEntity.ok(adminService.suspendDriver(userId, reason));
    }
}
