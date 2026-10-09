package com.quickdrop.quickdrop.identity.dto;

import com.quickdrop.quickdrop.identity.enums.Role;
import com.quickdrop.quickdrop.identity.enums.UserStatus;
import com.quickdrop.quickdrop.vehicle.dto.VehicleModelDto;
import com.quickdrop.quickdrop.vehicle.dto.VehicleTypeResponseDto;

import java.time.LocalDate;

public class UserProfileDto {
    private Long id;
    private String mobile;
    private boolean onboarded;
    private String name;
    private String email;
    private Role role;
    private UserStatus status;

    // Driver details (populated if role == DRIVER)
    private VehicleTypeResponseDto vehicleType;
    private VehicleModelDto vehicleModel;
    private String customVehicleModel;
    private String vehicleNumber;
    private String licenseNumber;
    private String insuranceNumber;
    private LocalDate insuranceExpiryDate;

    public UserProfileDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isOnboarded() {
        return onboarded;
    }

    public void setOnboarded(boolean onboarded) {
        this.onboarded = onboarded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public VehicleTypeResponseDto getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeResponseDto vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleModelDto getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModelDto vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getCustomVehicleModel() {
        return customVehicleModel;
    }

    public void setCustomVehicleModel(String customVehicleModel) {
        this.customVehicleModel = customVehicleModel;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public LocalDate getInsuranceExpiryDate() {
        return insuranceExpiryDate;
    }

    public void setInsuranceExpiryDate(LocalDate insuranceExpiryDate) {
        this.insuranceExpiryDate = insuranceExpiryDate;
    }
}
