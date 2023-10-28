package com.spoofy.aeroclub.infrastructure.adapter;

import com.spoofy.aeroclub.infrastructure.entity.PlaneEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlaneCrudRepository extends CrudRepository<PlaneEntity, UUID> {

    PlaneEntity findByRegistration(String registration);

}
