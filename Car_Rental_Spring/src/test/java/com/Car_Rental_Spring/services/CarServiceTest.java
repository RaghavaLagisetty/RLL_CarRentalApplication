package com.Car_Rental_Spring.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCarById() {
        // Given
        long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // When
        Car result = carService.getCarById(carId);

        // Then
        assertNotNull(result);
        assertEquals(carId, result.getId());
    }

    @Test
    void testGetCarByIdNotFound() {
        // Given
        long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(EntityNotFoundException.class, () -> carService.getCarById(carId));
    }

    @Test
    void testGetAllCarsByModel() {
        // Given
        String model = "Toyota";
        Car car1 = new Car();
        car1.setModel("Toyota Corolla");
        Car car2 = new Car();
        car2.setModel("Toyota Camry");
        List<Car> cars = Arrays.asList(car1, car2);
        when(carRepository.findAllByModelContainingIgnoreCase(model)).thenReturn(cars);

        // When
        List<Car> result = carService.getAllCarsByModel(model);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Toyota Corolla", result.get(0).getModel());
        assertEquals("Toyota Camry", result.get(1).getModel());
    }
}
