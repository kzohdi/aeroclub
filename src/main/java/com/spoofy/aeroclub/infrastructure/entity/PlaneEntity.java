package com.spoofy.aeroclub.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity(name = "plane")
@Data
public class PlaneEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String registration;

    private String brand;

    private String model;

    private int nbSeats;

}
