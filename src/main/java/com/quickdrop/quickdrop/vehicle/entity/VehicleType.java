package com.quickdrop.quickdrop.vehicle.entity;

import com.quickdrop.quickdrop.common.entity.Auditable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle_types")
public class VehicleType extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Human-readable label shown in UI dropdown (e.g., "Two Wheeler / Bike")
    @Column(nullable = false, length = 100)
    private String name;

    // Unique machine code used in API requests (e.g., "BIKE", "MINI_TRUCK")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    // Maximum cargo weight this vehicle can carry (in kg)
    @Column
    private Double maxCapacityKg;

    // Admin can deactivate a vehicle type without deleting it
    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "vehicleType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VehicleModel> models = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMaxCapacityKg() {
        return maxCapacityKg;
    }

    public void setMaxCapacityKg(Double maxCapacityKg) {
        this.maxCapacityKg = maxCapacityKg;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<VehicleModel> getModels() {
        return models;
    }

    public void setModels(List<VehicleModel> models) {
        this.models = models;
    }
}

