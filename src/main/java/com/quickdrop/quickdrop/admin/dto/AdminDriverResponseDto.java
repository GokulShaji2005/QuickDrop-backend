package com.quickdrop.quickdrop.admin.dto;

import com.quickdrop.quickdrop.identity.enums.Role;
import com.quickdrop.quickdrop.identity.enums.UserStatus;
import com.quickdrop.quickdrop.vehicle.dto.VehicleModelDto;
import com.quickdrop.quickdrop.vehicle.dto.VehicleTypeResponseDto;

import java.time.LocalDate;

public class AdminDriverResponseDto {

    private Long userId;
    private Long personId;
    private String name;
    private String email;
    private String mobile;
    private Role role;
    private UserStatus status;

    // Driver vehicle & credential details
    private VehicleTypeResponseDto vehicleType;
    private VehicleModelDto vehicleModel;
    private String customVehicleModel;
    private String vehicleNumber;
    private String licenseNumber;
    private String insuranceNumber;
    private LocalDate insuranceExpiryDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
