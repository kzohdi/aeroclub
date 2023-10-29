package com.spoofy.aeroclub.domain.port.in.impl;

import com.spoofy.aeroclub.domain.model.Plane;
import com.spoofy.aeroclub.domain.port.in.PlaneService;
import com.spoofy.aeroclub.domain.port.out.PlaneRepository;
import com.spoofy.aeroclub.exception.NotFoundException;
import com.spoofy.aeroclub.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;

    @Override
    public Plane createPlane(Plane plane) {
        Plane existingPlane = planeRepository.findPlane(plane.getRegistration());
        if (null != existingPlane) {
            throw new ServerException("The plane you are trying to create already exists, try another one.");
        }
        return planeRepository.createPlane(plane);
    }

    @Override
    public Plane findPlane(String registration) {
        Plane existingPlane = planeRepository.findPlane(registration);
        if (null == existingPlane) {
            throw new NotFoundException(String.format("The plane with the '%s' registration was not found.", registration));
        }
        return existingPlane;
    }
}
