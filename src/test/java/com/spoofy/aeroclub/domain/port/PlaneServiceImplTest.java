package com.spoofy.aeroclub.domain.port;

import com.spoofy.aeroclub.domain.model.Plane;
import com.spoofy.aeroclub.domain.port.in.impl.PlaneServiceImpl;
import com.spoofy.aeroclub.domain.port.out.PlaneRepository;
import com.spoofy.aeroclub.common.exception.NotFoundException;
import com.spoofy.aeroclub.common.exception.ServerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaneServiceImplTest {

    @InjectMocks
    private PlaneServiceImpl planeService;

    @Mock
    private PlaneRepository planeRepository;

    private static Plane planeMock;

    @BeforeAll
    static void setup() {
        planeMock = Plane.builder()
                .registration("registration")
                .brand("brand")
                .model("model")
                .nbSeats(2)
                .build();
    }

    @Test
    void whenCreatePlaneWithNewRegistrationShouldReturnNewPlane() {
        // When
        when(planeRepository.findPlane(anyString())).thenReturn(null);
        when(planeRepository.createPlane(any(Plane.class))).thenReturn(planeMock);

        // Then
        Plane createdPlane = planeService.createPlane(planeMock);

        assertNotNull(createdPlane);
        assertEquals(planeMock.getRegistration(), createdPlane.getRegistration());
        assertEquals(planeMock.getBrand(), createdPlane.getBrand());
        assertEquals(planeMock.getModel(), createdPlane.getModel());
        assertEquals(planeMock.getNbSeats(), createdPlane.getNbSeats());
    }

    @Test
    void whenCreatePlaneWithExistingRegistrationShouldThrowException() {
        // When
        when(planeRepository.findPlane(anyString())).thenReturn(planeMock);

        // Then
        Exception exception = assertThrows(ServerException.class, () -> planeService.createPlane(planeMock));

        assertEquals("The plane you are trying to create already exists, try another one.", exception.getMessage());
        verify(planeRepository, times(0)).createPlane(any(Plane.class));

    }

    @Test
    void whenFindPlaneWithExistingRegistrationShouldReturnPlane() {
        // When
        when(planeRepository.findPlane(anyString())).thenReturn(planeMock);

        // Then
        Plane foundPlane = planeService.findPlane("registration");

        assertNotNull(foundPlane);
        assertEquals(planeMock.getRegistration(), foundPlane.getRegistration());
        assertEquals(planeMock.getBrand(), foundPlane.getBrand());
        assertEquals(planeMock.getModel(), foundPlane.getModel());
        assertEquals(planeMock.getNbSeats(), foundPlane.getNbSeats());
    }

    @Test
    void whenFindPlaneWithUnknownRegistrationShouldThrowException() {
        // When
        when(planeRepository.findPlane(anyString())).thenReturn(null);

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> planeService.findPlane("registration"));

        assertEquals("The plane with the 'registration' registration was not found.", exception.getMessage());
    }
}