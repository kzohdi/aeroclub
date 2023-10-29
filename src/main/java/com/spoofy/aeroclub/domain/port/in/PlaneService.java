package com.spoofy.aeroclub.domain.port.in;

import com.spoofy.aeroclub.domain.model.Plane;

public interface PlaneService {

    /**
     * Service used to create a new plane
     *
     * @param plane The place to create
     */
    Plane createPlane(Plane plane);

    /**
     * Service used to find a plane by its registration
     * @param registration
     * @return
     */
    Plane findPlane(String registration);
}
