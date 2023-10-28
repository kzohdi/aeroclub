package com.spoofy.aeroclub.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Plane {

    @NotBlank
    private String registration;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Positive
    private int nbSeats;

}
