package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.dto.RentalDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.CarType;
import com.Car_Rental_Spring.entity.Rental;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.enums.RentalStatus;
import com.Car_Rental_Spring.repository.CarRepository;
import com.Car_Rental_Spring.repository.RentalRepository;
import com.Car_Rental_Spring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarRentalServiceTest {

    @InjectMocks
    private CarRentalService carRentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        // Mocking the behavior of rentalRepository
        when(rentalRepository.findAll()).thenReturn(rentals);

        List<Rental> result = carRentalService.getAllRentals();

        assertEquals(rentals, result);
    }

    @Test
    void testRentCar() {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setUserId(1L);
        rentalDTO.setCarId(1L);
        rentalDTO.setStartOdometerReading(100);
        rentalDTO.setEndOdometerReading(200);
        rentalDTO.setStartDate(LocalDate.now());
        rentalDTO.setEndDate(LocalDate.now().plusDays(3));

        User user = new User();
        user.setRoyaltyPoints(30);
        // Mocking the behavior of userRepository
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Car car = new Car();
        CarType carType = new CarType();
        carType.setTaxRate(BigDecimal.TEN);
        car.setCostPerKilometer(BigDecimal.valueOf(5));
        car.setCartype(carType);
        // Mocking the behavior of carRepository
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Rental rental = new Rental();
        rental.setId(1L);
        // Mocking the behavior of rentalRepository
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        Rental result = carRentalService.rentCar(rentalDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testApplyPointsDiscount() {
        User user = new User();
        user.setRoyaltyPoints(30);
        BigDecimal totalAmount = BigDecimal.valueOf(100);

        // Mocking the behavior of userRepository
        when(userRepository.save(any(User.class))).thenReturn(user);

        BigDecimal result = carRentalService.applyPointsDiscount(user, totalAmount);

        assertNotNull(result);
    }


    @Test
    void testUpdateRentalStatus() {
        // Create a mock Rental object
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setRentalStatus(RentalStatus.CREATED);
        rental.setCar(new Car());
        rental.setEndOdometerReading(200);
        rental.setTotalDistance(100);
        rental.setUser(new User());

        // Mock the behavior of rentalRepository.findById
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        // Mock the behavior of rentalRepository.save
        when(rentalRepository.save(any(Rental.class))).thenAnswer(invocation -> {
            Rental savedRental = invocation.getArgument(0);
            savedRental.setId(1L);
            return savedRental;
        });

        // Call the method to be tested
        Rental result = carRentalService.updateRentalStatus(1L, "APPROVED");

        // Assert the result
        assertNotNull(result);
        assertEquals(RentalStatus.APPROVED, result.getRentalStatus());
    }

}
