package com.quickdrop.quickdrop.identity.dto;

import com.quickdrop.quickdrop.identity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class OnboardRequestDto {

    @NotNull(message = "Role is required (SENDER, RECEIVER, or DRIVER)")
    private Role role;

    @NotBlank(message = "Name must not be blank")
    private String name;

    private String email;

    // Driver-specific fields (required if role == DRIVER)
    private Long vehicleTypeId;

    private Long vehicleModelId;

    private String customVehicleModel;

    private String vehicleNumber;

    private String licenseNumber;

    private String insuranceNumber;

    private LocalDate insuranceExpiryDate;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
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
