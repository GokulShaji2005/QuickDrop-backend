package com.quickdrop.quickdrop.vehicle.repository;

import com.quickdrop.quickdrop.vehicle.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    // Used by GET /vehicle-types to populate driver onboarding dropdown
    List<VehicleType> findAllByActiveTrue();

    // Used by UserService.onboard() to validate the driver's selected vehicle code
    Optional<VehicleType> findByCodeAndActiveTrue(String code);
}
