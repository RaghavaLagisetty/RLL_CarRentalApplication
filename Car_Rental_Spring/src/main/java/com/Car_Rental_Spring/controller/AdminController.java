package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.CarType;
import com.Car_Rental_Spring.entity.Rental;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public boolean adminLogin(@RequestParam String email, @RequestParam String password) {
        return adminService.adminLogin(email, password);
    }

//    @PostMapping("/create-admin")
//    public void createAdminAccount(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
//        adminService.createAdminAccount(username, password, email);
//    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return adminService.getAllCars();
    }

    @GetMapping("/car-types")
    public List<CarType> getAllCarTypes() {
        return adminService.getAllCarTypes();
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestParam("image") MultipartFile image,
                      @RequestParam("make") String make,
                      @RequestParam("model") String model,
                      @RequestParam("year") int year,
                      @RequestParam("odometerReading") int odometerReading,
                      @RequestParam("cartype") Long cartype,
                      @RequestParam("costPerKilometer") BigDecimal costPerKilometer) {
        try {
            Car createdCar = adminService.addCar(make,model, year, odometerReading, cartype, costPerKilometer, image);
            return new ResponseEntity<>(createdCar, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image,
                                    @RequestParam("make") String make,
                                    @RequestParam("model") String model,
                                    @RequestParam("year") int year,
                                    @RequestParam("odometerReading") int odometerReading,
                                    @RequestParam("cartype") Long cartype,
                                    @RequestParam("costPerKilometer") BigDecimal costPerKilometer) {
        try {
            Car createdCar = adminService.updateCar(id, make,model, year, odometerReading, cartype, costPerKilometer, image);
            return new ResponseEntity<>(createdCar, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        try {
            adminService.deleteCar(carId);
            return new ResponseEntity<>("Car and Rentals Deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/manage-tax-rate")
    public void manageTaxRate(@RequestParam String carType, @RequestParam double taxRate) {
        adminService.manageTaxRate(carType, taxRate);
    }

    @GetMapping("/rentals")
    public List<Rental> viewAllRentals() {
        return adminService.viewAllRentals();
    }

    @GetMapping("/rentals/{userId}")
    public List<Rental> viewRentalHistory(@PathVariable Long userId) {
        return adminService.viewRentalHistory(userId);
    }

    @PostMapping("/mark-car-as-rented")
    public void markCarAsRented(@RequestParam Long carId, @RequestParam LocalDate rentalDate) {
        adminService.markCarAsRented(carId, rentalDate);
    }

    @GetMapping("/calculate-rental-cost")
    public BigDecimal calculateRentalCost(@RequestBody Car car, @RequestParam int distance, @RequestParam int rentalDuration, @RequestParam int loyaltyPoints) {
        return adminService.calculateRentalCost(car, distance, rentalDuration, loyaltyPoints);
    }
}
