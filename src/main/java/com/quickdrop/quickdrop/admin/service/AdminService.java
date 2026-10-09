package com.quickdrop.quickdrop.admin.service;

import com.quickdrop.quickdrop.admin.dto.AdminDriverResponseDto;
import com.quickdrop.quickdrop.common.exceptions.InvalidInputException;
import com.quickdrop.quickdrop.common.exceptions.ResourceNotFoundException;
import com.quickdrop.quickdrop.identity.entity.DriverProfile;
import com.quickdrop.quickdrop.identity.entity.User;
import com.quickdrop.quickdrop.identity.enums.Role;
import com.quickdrop.quickdrop.identity.enums.UserStatus;
import com.quickdrop.quickdrop.identity.repository.DriverProfileRepository;
import com.quickdrop.quickdrop.identity.repository.UserRepository;
import com.quickdrop.quickdrop.vehicle.dto.VehicleModelDto;
import com.quickdrop.quickdrop.vehicle.service.VehicleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final DriverProfileRepository driverProfileRepository;
    private final VehicleTypeService vehicleTypeService;

    public AdminService(UserRepository userRepository,
                        DriverProfileRepository driverProfileRepository,
                        VehicleTypeService vehicleTypeService) {
        this.userRepository = userRepository;
        this.driverProfileRepository = driverProfileRepository;
        this.vehicleTypeService = vehicleTypeService;
    }

    @Transactional(readOnly = true)
    public Page<AdminDriverResponseDto> getPendingDrivers(Pageable pageable) {
        Page<User> users = userRepository.findByRoleAndStatus(Role.DRIVER, UserStatus.UNDER_REVIEW, pageable);
        return users.map(this::mapToAdminDriverResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<AdminDriverResponseDto> getDriversByStatus(UserStatus status, Pageable pageable) {
        Page<User> users = status != null ?
                userRepository.findByRoleAndStatus(Role.DRIVER, status, pageable) :
                userRepository.findByRole(Role.DRIVER, pageable);
        return users.map(this::mapToAdminDriverResponseDto);
    }

    @Transactional
    public AdminDriverResponseDto approveDriver(Long userId) {
        User user = findDriverUser(userId);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return mapToAdminDriverResponseDto(user);
    }

    @Transactional
    public AdminDriverResponseDto rejectDriver(Long userId, String reason) {
        User user = findDriverUser(userId);
        user.setStatus(UserStatus.DEACTIVATED);
        userRepository.save(user);
        return mapToAdminDriverResponseDto(user);
    }

    @Transactional
    public AdminDriverResponseDto suspendDriver(Long userId, String reason) {
        User user = findDriverUser(userId);
        user.setStatus(UserStatus.SUSPENDED);
        userRepository.save(user);
        return mapToAdminDriverResponseDto(user);
    }

    private User findDriverUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        if (user.getRole() != Role.DRIVER) {
            throw new InvalidInputException("User with ID " + userId + " is not a driver");
        }

        return user;
    }

    private AdminDriverResponseDto mapToAdminDriverResponseDto(User user) {
        AdminDriverResponseDto dto = new AdminDriverResponseDto();
        dto.setUserId(user.getId());
        dto.setPersonId(user.getPerson().getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getPerson().getMobile());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        Optional<DriverProfile> profileOpt = driverProfileRepository.findByUserId(user.getId());
        if (profileOpt.isPresent()) {
            DriverProfile profile = profileOpt.get();
            if (profile.getVehicleType() != null) {
                dto.setVehicleType(vehicleTypeService.mapToResponseDto(profile.getVehicleType()));
            }
            if (profile.getVehicleModel() != null) {
                dto.setVehicleModel(new VehicleModelDto(profile.getVehicleModel().getId(), profile.getVehicleModel().getName()));
            }
            dto.setCustomVehicleModel(profile.getCustomVehicleModel());
            dto.setVehicleNumber(profile.getVehicleNumber());
            dto.setLicenseNumber(profile.getLicenseNumber());
            dto.setInsuranceNumber(profile.getInsuranceNumber());
            dto.setInsuranceExpiryDate(profile.getInsuranceExpiryDate());
        }

        return dto;
    }
}
