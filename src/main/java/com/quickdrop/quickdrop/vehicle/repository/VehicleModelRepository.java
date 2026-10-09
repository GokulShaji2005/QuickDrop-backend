package com.quickdrop.quickdrop.vehicle.repository;

import com.quickdrop.quickdrop.vehicle.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    List<VehicleModel> findByVehicleTypeIdAndActiveTrue(Long vehicleTypeId);

    List<VehicleModel> findByVehicleTypeCodeAndActiveTrue(String vehicleTypeCode);
}
