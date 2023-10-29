package com.spoofy.aeroclub.infrastructure.adapter;

import com.spoofy.aeroclub.infrastructure.entity.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneJpaRepository extends JpaRepository<PlaneEntity, String> {

    Optional<PlaneEntity> findByRegistration(String registration);

}
