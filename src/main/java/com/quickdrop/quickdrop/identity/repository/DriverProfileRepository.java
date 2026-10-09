package com.quickdrop.quickdrop.identity.repository;

import com.quickdrop.quickdrop.identity.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverProfileRepository extends JpaRepository<DriverProfile, Long> {

    Optional<DriverProfile> findByUserId(Long userId);

    Optional<DriverProfile> findByUserPersonId(Long personId);
}
