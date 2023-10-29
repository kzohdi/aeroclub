package com.spoofy.aeroclub.domain.port.out;

import com.spoofy.aeroclub.domain.model.Plane;

public interface PlaneRepository {

    Plane createPlane(Plane plane);

    Plane findPlane(String registration);
}
