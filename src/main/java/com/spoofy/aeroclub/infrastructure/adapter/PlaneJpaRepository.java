package com.spoofy.aeroclub.infrastructure.adapter;

import com.spoofy.aeroclub.infrastructure.entity.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneJpaRepository extends JpaRepository<PlaneEntity, String> {

    PlaneEntity findByRegistration(String registration);

}
