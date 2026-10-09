package com.quickdrop.quickdrop.identity.entity;

import com.quickdrop.quickdrop.common.entity.Auditable;
import com.quickdrop.quickdrop.vehicle.entity.VehicleModel;
import com.quickdrop.quickdrop.vehicle.entity.VehicleType;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver_profiles")
public class DriverProfile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    @Column(length = 100)
    private String customVehicleModel;

    @Column(length = 30)
    private String vehicleNumber;

    @Column(length = 50)
    private String licenseNumber;

    @Column(length = 50)
    private String insuranceNumber;

    @Column
    private LocalDate insuranceExpiryDate;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
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
