package com.quickdrop.quickdrop.vehicle.dto;

import java.util.List;

public class VehicleTypeResponseDto {
    private Long id;
    private String name;
    private String code;
    private Double maxCapacityKg;
    private List<VehicleModelDto> models;

    public VehicleTypeResponseDto() {}

    public VehicleTypeResponseDto(Long id, String name, String code, Double maxCapacityKg, List<VehicleModelDto> models) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.maxCapacityKg = maxCapacityKg;
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<VehicleModelDto> getModels() {
        return models;
    }

    public void setModels(List<VehicleModelDto> models) {
        this.models = models;
    }
}
