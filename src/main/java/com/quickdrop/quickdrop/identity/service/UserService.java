package com.quickdrop.quickdrop.identity.service;

import com.quickdrop.quickdrop.common.exceptions.DuplicateResourceException;
import com.quickdrop.quickdrop.common.exceptions.InvalidInputException;
import com.quickdrop.quickdrop.common.exceptions.ResourceNotFoundException;
import com.quickdrop.quickdrop.identity.dto.OnboardRequestDto;
import com.quickdrop.quickdrop.identity.dto.UserProfileDto;
import com.quickdrop.quickdrop.identity.entity.DriverProfile;
import com.quickdrop.quickdrop.identity.entity.Person;
import com.quickdrop.quickdrop.identity.entity.User;
import com.quickdrop.quickdrop.identity.enums.Role;
import com.quickdrop.quickdrop.identity.enums.UserStatus;
import com.quickdrop.quickdrop.identity.repository.DriverProfileRepository;
import com.quickdrop.quickdrop.identity.repository.PersonRepository;
import com.quickdrop.quickdrop.identity.repository.UserRepository;
import com.quickdrop.quickdrop.vehicle.dto.VehicleModelDto;
import com.quickdrop.quickdrop.vehicle.entity.VehicleModel;
import com.quickdrop.quickdrop.vehicle.entity.VehicleType;
import com.quickdrop.quickdrop.vehicle.repository.VehicleModelRepository;
import com.quickdrop.quickdrop.vehicle.service.VehicleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final DriverProfileRepository driverProfileRepository;
    private final VehicleTypeService vehicleTypeService;
    private final VehicleModelRepository vehicleModelRepository;

    public UserService(PersonRepository personRepository,
                       UserRepository userRepository,
                       DriverProfileRepository driverProfileRepository,
                       VehicleTypeService vehicleTypeService,
                       VehicleModelRepository vehicleModelRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.driverProfileRepository = driverProfileRepository;
        this.vehicleTypeService = vehicleTypeService;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    @Transactional(readOnly = true)
    public UserProfileDto getProfile(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        UserProfileDto dto = new UserProfileDto();
        dto.setId(person.getId());
        dto.setMobile(person.getMobile());
        dto.setOnboarded(person.isOnboarded());

        if (person.isOnboarded()) {
            List<User> users = userRepository.findByPersonId(personId);
            if (!users.isEmpty()) {
                User user = users.get(0); // primary user profile
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setRole(user.getRole());
                dto.setStatus(user.getStatus());

                if (user.getRole() == Role.DRIVER) {
                    Optional<DriverProfile> driverOpt = driverProfileRepository.findByUserId(user.getId());
                    if (driverOpt.isPresent()) {
                        DriverProfile driver = driverOpt.get();
                        dto.setVehicleType(vehicleTypeService.mapToResponseDto(driver.getVehicleType()));
                        if (driver.getVehicleModel() != null) {
                            dto.setVehicleModel(new VehicleModelDto(driver.getVehicleModel().getId(), driver.getVehicleModel().getName()));
                        }
                        dto.setCustomVehicleModel(driver.getCustomVehicleModel());
                        dto.setVehicleNumber(driver.getVehicleNumber());
                        dto.setLicenseNumber(driver.getLicenseNumber());
                        dto.setInsuranceNumber(driver.getInsuranceNumber());
                        dto.setInsuranceExpiryDate(driver.getInsuranceExpiryDate());
                    }
                }
            }
        }

        return dto;
    }

    @Transactional
    public UserProfileDto onboard(Long personId, OnboardRequestDto dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with ID: " + personId));

        if (person.isOnboarded()) {
            throw new DuplicateResourceException("User is already onboarded");
        }

        User user = new User();
        user.setPerson(person);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        if (dto.getRole() == Role.DRIVER) {
            if (dto.getVehicleTypeId() == null) {
                throw new InvalidInputException("Vehicle type is required for driver onboarding");
            }
            VehicleType vehicleType = vehicleTypeService.getById(dto.getVehicleTypeId());

            user.setStatus(UserStatus.UNDER_REVIEW);
            userRepository.save(user);

            DriverProfile driverProfile = new DriverProfile();
            driverProfile.setUser(user);
            driverProfile.setVehicleType(vehicleType);

            if (dto.getVehicleModelId() != null) {
                VehicleModel model = vehicleModelRepository.findById(dto.getVehicleModelId())
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicle model not found with ID: " + dto.getVehicleModelId()));
                driverProfile.setVehicleModel(model);
            }

            driverProfile.setCustomVehicleModel(dto.getCustomVehicleModel());
            driverProfile.setVehicleNumber(dto.getVehicleNumber());
            driverProfile.setLicenseNumber(dto.getLicenseNumber());
            driverProfile.setInsuranceNumber(dto.getInsuranceNumber());
            driverProfile.setInsuranceExpiryDate(dto.getInsuranceExpiryDate());

            driverProfileRepository.save(driverProfile);
        } else {
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        }

        person.setOnboarded(true);
        personRepository.save(person);

        return getProfile(personId);
    }
}
