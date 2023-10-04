package com.threepm.threepm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threepm.threepm.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
    
}
