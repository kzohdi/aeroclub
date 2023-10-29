package com.spoofy.aeroclub.infrastructure.adapter.impl;

import com.spoofy.aeroclub.domain.model.Plane;
import com.spoofy.aeroclub.domain.port.out.PlaneRepository;
import com.spoofy.aeroclub.infrastructure.adapter.PlaneJpaRepository;
import com.spoofy.aeroclub.infrastructure.mapper.PlaneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaneRepositoryImpl implements PlaneRepository {

    private final PlaneJpaRepository planeJpaRepository;
    private final PlaneMapper planeMapper;

    @Override
    public Plane createPlane(Plane plane) {
        return planeMapper.toPlane(planeJpaRepository.save(planeMapper.toPlaneEntity(plane)));
    }

    @Override
    public Plane findPlane(String registration) {
        return planeMapper.toPlane(planeJpaRepository.findByRegistration(registration));
    }
}
