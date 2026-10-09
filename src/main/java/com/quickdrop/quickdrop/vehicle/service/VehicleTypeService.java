package com.quickdrop.quickdrop.vehicle.service;

import com.quickdrop.quickdrop.common.exceptions.ResourceNotFoundException;
import com.quickdrop.quickdrop.vehicle.dto.VehicleModelDto;
import com.quickdrop.quickdrop.vehicle.dto.VehicleTypeResponseDto;
import com.quickdrop.quickdrop.vehicle.entity.VehicleType;
import com.quickdrop.quickdrop.vehicle.repository.VehicleTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    // Returns all active vehicle types with nested active models for GET /vehicle-types
    @Transactional(readOnly = true)
    public List<VehicleTypeResponseDto> getActiveVehicleTypesWithModels() {
        return vehicleTypeRepository.findAllByActiveTrue().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Lookup entity by ID (used during onboarding)
    @Transactional(readOnly = true)
    public VehicleType getById(Long id) {
        return vehicleTypeRepository.findById(id)
                .filter(VehicleType::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle type not found or inactive with ID: " + id));
    }

    public VehicleTypeResponseDto mapToResponseDto(VehicleType vt) {
        List<VehicleModelDto> modelDtos = vt.getModels().stream()
                .filter(m -> m.isActive())
                .map(m -> new VehicleModelDto(m.getId(), m.getName()))
                .collect(Collectors.toList());

        return new VehicleTypeResponseDto(vt.getId(), vt.getName(), vt.getCode(), vt.getMaxCapacityKg(), modelDtos);
    }
}
