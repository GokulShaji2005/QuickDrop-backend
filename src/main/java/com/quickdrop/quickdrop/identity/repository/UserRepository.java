package com.quickdrop.quickdrop.identity.repository;

import com.quickdrop.quickdrop.identity.enums.*;

import com.quickdrop.quickdrop.identity.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPersonId(Long personId);

    Optional<User> findByPersonIdAndRole(Long personId, Role role);
}
