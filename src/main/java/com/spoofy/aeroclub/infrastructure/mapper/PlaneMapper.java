package com.spoofy.aeroclub.infrastructure.mapper;

import com.spoofy.aeroclub.domain.model.Plane;
import com.spoofy.aeroclub.infrastructure.entity.PlaneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaneMapper {

    Plane toPlane(PlaneEntity planeEntity);

    PlaneEntity toPlaneEntity(Plane plane);
}
