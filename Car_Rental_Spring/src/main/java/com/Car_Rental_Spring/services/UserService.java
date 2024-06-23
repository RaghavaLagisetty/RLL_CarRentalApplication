package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.dto.UpdatePasswordRequestDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.repository.UserRepository;
import com.Car_Rental_Spring.entity.Rental;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // User registration
    public User registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser==null){
            user.setRole(User.UserRole.USER);
            user.setRoyaltyPoints(0);
            return userRepository.save(user);
        }
        throw new EntityExistsException("User Already Present WIth Same Email");
    }

    // User login
    public User userLogin(String email, String password) {
        User storedUser = userRepository.findByEmail(email);
        if(storedUser == null){
            throw  new EntityNotFoundException("User not found.");
        }

        if (storedUser.getPassword().equals(password)) {
            return storedUser;
        } else {
            throw new EntityNotFoundException("Wrong password.");
        }
    }

    public User getUserDetails(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new EntityNotFoundException("User Not Found");
    }

    // Change password
    public User changePassword(UpdatePasswordRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getId());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPassword().equals(dto.getCurrentPassword())){
                user.setPassword(dto.getNewPassword());
                return userRepository.save(user);
            }else{
                throw new EntityNotFoundException("Current Password is Wrong.");
            }
        }
        throw new EntityNotFoundException("User Not Found.");
    }

    // Update user profile
    public void updateProfile(User user) {
        // Implement update user profile logic here
    }

    // Search for cars
    public List<Car> searchCars(String keyword) {
        // Implement car search logic here
        return null;
    }

    // Rent a car
    public void rentCar(Long userId, Long carId, int distance) {
        // Implement rent a car logic here
    }

    // View previous rentals
    public List<Rental> viewPreviousRentals(Long userId) {
        // Implement view previous rentals logic here
        return null;
    }

    // View future rentals
    public List<Rental> viewFutureRentals(Long userId) {
        // Implement view future rentals logic here
        return null;
    }

    // Cancel booking
    public void cancelBooking(Long bookingId) {
        // Implement cancel booking logic here
    }

    // Calculate loyalty points
    public int calculateLoyaltyPoints(int distance) {
        // Implement calculate loyalty points logic here
        return 0;
    }

    // Calculate extra discount based on loyalty points
    public BigDecimal calculateExtraDiscount(int loyaltyPoints) {
        // Implement calculate extra discount logic here
        return null;
    }
}
