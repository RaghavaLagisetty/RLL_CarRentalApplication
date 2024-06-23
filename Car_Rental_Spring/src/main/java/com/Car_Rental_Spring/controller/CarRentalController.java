package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.dto.RentalDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.enums.RentalStatus;
import com.Car_Rental_Spring.services.CarRentalService;
import com.Car_Rental_Spring.services.UserService;
import com.Car_Rental_Spring.entity.CarType;
import com.Car_Rental_Spring.entity.Rental;
import com.Car_Rental_Spring.services.CarTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarRentalController {

    @Autowired
    private CarRentalService carRentalService;

    @Autowired
    private CarTypeService carTypeService;

    @Autowired
    private UserService userRoleService;

    // Car Rental endpoints

    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        return carRentalService.getAllRentals();
    }

    @PostMapping("/rentals")
    public ResponseEntity<?> rentCar(@RequestBody RentalDTO rentalDTO) {
        try {
            Rental createdRental = carRentalService.rentCar(rentalDTO);
            return new ResponseEntity<>(createdRental, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/rentals/{id}/{rentalStatus}")
    public ResponseEntity<?> updateRentalStatus(@PathVariable Long id, @PathVariable String rentalStatus) {
        try {
            Rental createdRental = carRentalService.updateRentalStatus(id, rentalStatus);
            return new ResponseEntity<>(createdRental, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/my-rentals/{userId}")
    public ResponseEntity<?> viewAllRentals(@PathVariable Long userId) {
        try {
            List<Rental> rentals = carRentalService.getRentalsByUser(userId);
            return new ResponseEntity<>(rentals, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // Car Type endpoints

    @GetMapping("/cartypes")
    public List<CarType> getAllCarTypes() {
        return carTypeService.getAllCarTypes();
    }

    @PostMapping("/cartypes")
    public CarType saveCarType(@RequestBody CarType carType) {
        return carTypeService.saveCarType(carType);
    }

    // User Role endpoints

    @GetMapping("/userroles")
    public List<User.UserRole> getAllUserRoles() {
        return Arrays.asList(User.UserRole.values()); // Assuming UserRole is an enum
    }

    @PostMapping("/userroles")
    public void createUserRole(@RequestBody User.UserRole userRole) {
        // You might not need to create user roles dynamically
        // UserRole is usually predefined and doesn't require creation
        // This endpoint may not be necessary depending on your use case
        throw new UnsupportedOperationException("Creating user roles dynamically is not supported.");
    }

}
