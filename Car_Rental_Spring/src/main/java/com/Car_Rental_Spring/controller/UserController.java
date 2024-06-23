package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.dto.UpdatePasswordRequestDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.services.CarService;
import com.Car_Rental_Spring.services.UserService;
import com.Car_Rental_Spring.entity.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.registerUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestParam String email, @RequestParam String password) {
        try {
            return new ResponseEntity<>(userService.userLogin(email, password), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long id) {
        try {
            User user = userService.getUserDetails(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UpdatePasswordRequestDTO dto) {
        try {
            User user = userService.changePassword(dto);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{model}")
    public ResponseEntity<?> searchCarsByModel(@PathVariable String model) {
        try {
            List<Car> carList = carService.getAllCarsByModel(model);
            return new ResponseEntity<>(carList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/change-password")
//    public void changePassword(@RequestParam String username, @RequestParam String newPassword) {
//        userService.changePassword(username, newPassword);
//    }

    @PostMapping("/update-profile")
    public void updateProfile(@RequestBody User user) {
        userService.updateProfile(user);
    }

    @GetMapping("/search-cars")
    public List<Car> searchCars(@RequestParam String keyword) {
        return userService.searchCars(keyword);
    }

    @PostMapping("/rent-car")
    public void rentCar(@RequestParam Long userId, @RequestParam Long carId, @RequestParam int distance) {
        userService.rentCar(userId, carId, distance);
    }

    @GetMapping("/previous-rentals")
    public List<Rental> viewPreviousRentals(@RequestParam Long userId) {
        return userService.viewPreviousRentals(userId);
    }

    @GetMapping("/future-rentals")
    public List<Rental> viewFutureRentals(@RequestParam Long userId) {
        return userService.viewFutureRentals(userId);
    }

    @PostMapping("/cancel-booking")
    public void cancelBooking(@RequestParam Long bookingId) {
        userService.cancelBooking(bookingId);
    }

    @GetMapping("/loyalty-points")
    public int calculateLoyaltyPoints(@RequestParam int distance) {
        return userService.calculateLoyaltyPoints(distance);
    }

    @GetMapping("/extra-discount")
    public BigDecimal calculateExtraDiscount(@RequestParam int loyaltyPoints) {
        return userService.calculateExtraDiscount(loyaltyPoints);
    }
}
