package com.spoofy.aeroclub.application.controller;

import com.spoofy.aeroclub.domain.model.Plane;
import com.spoofy.aeroclub.domain.port.in.PlaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plane")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping
    public Plane createPlane(@RequestBody @Valid Plane plane) {
        return planeService.createPlane(plane);
    }

    @GetMapping("/{registration}")
    public Plane findPlane(@PathVariable String registration) {
        return planeService.findPlane(registration);
    }
}
